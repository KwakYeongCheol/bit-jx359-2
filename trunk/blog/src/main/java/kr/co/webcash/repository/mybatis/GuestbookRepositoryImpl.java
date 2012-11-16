package kr.co.webcash.repository.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Guestbook;
import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.comment.CommentType;
import kr.co.webcash.repository.CommentRepository;
import kr.co.webcash.repository.GuestbookRepository;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GuestbookRepositoryImpl implements GuestbookRepository{
	@Autowired private SqlSession sqlSession;
	@Autowired CommentRepository commentRepository;
	
	private List<Guestbook> wrap(List<Guestbook> guestbookList){
		for(Guestbook guestbook : guestbookList){
			wrap(guestbook);
		}
		
		return guestbookList;
	}
	
	private Guestbook wrap(Guestbook guestbook){
		if(guestbook != null)	addComments(guestbook);
		
		return guestbook;
	}
	
	private void addComments(Guestbook guestbook) {
		guestbook.setCommentList(commentRepository.findAllByTargetIdAndType(guestbook.getId(), CommentType.guestbook));
	}
	
	@Override
	public void insert(Guestbook guestbook) {
		sqlSession.insert("Guestbook.insert",guestbook);
	}
	
	@Override
	public void update(Guestbook guestbook) {
		sqlSession.update("Guestbook.update", guestbook);
	}
	
	@Override
	public void delete(Guestbook guestbook) {
		sqlSession.delete("Guestbook.delete", guestbook);
	}
	
	@Override
	public Guestbook findLastGuestbookByBlogId(String blogId) {
		return wrap(sqlSession.<Guestbook>selectOne("Guestbook.findLastGuestbookByBlogId", blogId));
	}
	
	@Override
	public Guestbook findLast() {
		return wrap(sqlSession.<Guestbook>selectOne("Guestbook.findLast"));
	}
	
	@Override
	public Guestbook findByBlogIdAndDisplayId(String blogId, long displayId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("blogId", blogId);
		param.put("displayId", displayId);
		
		return wrap(sqlSession.<Guestbook>selectOne("Guestbook.findByBlogIdAndDisplayId", param));
	}
	
	@Override
	public List<Guestbook> findAllByBlogId(String blogId) {
		return wrap(sqlSession.<Guestbook>selectList("Guestbook.findAllByBlogId", blogId));
	}

	@Override
	public List<Guestbook> findAllByBlogIdAndPageNumberAndPageSize(String blogId, int pageNumber, int pageSize) {
		return wrap(sqlSession.<Guestbook>selectList("Guestbook.findAllByBlogId", blogId, new RowBounds((pageNumber-1) * pageSize, pageSize)));
	}

	@Override
	public List<Guestbook> findAllByBlogIdAndPage(String blogId, Page page) {
		return findAllByBlogIdAndPageNumberAndPageSize(blogId, page.getCurrentPage(), page.getPageSize());
	}

	@Override
	public int countByBlogId(String blogId) {
		return sqlSession.<Integer>selectOne("Guestbook.countByBlogId", blogId);
	}
}
