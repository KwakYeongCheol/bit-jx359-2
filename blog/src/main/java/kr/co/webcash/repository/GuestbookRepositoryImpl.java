package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.CommentType;
import kr.co.webcash.domain.Guestbook;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GuestbookRepositoryImpl implements GuestbookRepository{

	@Autowired private SqlSession sqlSession;
	@Autowired CommentRepository commentRepository;
	
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
		Guestbook guestbook = (Guestbook) sqlSession.selectOne("Guestbook.findLastGuestbookByBlogId", blogId);
		addComments(guestbook);
		
		return guestbook;
	}
	@Override
	public List<Guestbook> findAllByBlogId(String blogId) {
		List<Guestbook> guestbookList = sqlSession.selectList("Guestbook.findAllByBlogId", blogId);
		addComments(guestbookList);
		
		return guestbookList;
	}
	@Override
	public Guestbook findByBlogIdAndDisplayId(String blogId, long displayId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("blogId", blogId);
		param.put("displayId", displayId);
		
		Guestbook guestbook = (Guestbook)sqlSession.selectOne("Guestbook.findByBlogIdAndDisplayId", param);
		addComments(guestbook);
		
		return guestbook;
	}
	
	@Override
	public Guestbook findLast() {
		Guestbook guestbook = (Guestbook) sqlSession.selectOne("Guestbook.findLast");
		
		addComments(guestbook);
		return guestbook;
	}
	
	
	private void addComments(Guestbook guestbook) {
		if(guestbook != null){
			guestbook.setCommentList(commentRepository.findAllByTargetIdAndType(guestbook.getId(), CommentType.guestbook));
		}
	}	
	private void addComments(List<Guestbook> guestbookList){
		for(Guestbook guestbook : guestbookList){
			addComments(guestbook);
		}
	}

}
