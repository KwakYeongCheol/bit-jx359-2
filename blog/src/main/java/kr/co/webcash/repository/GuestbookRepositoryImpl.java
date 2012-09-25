package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;


import kr.co.webcash.domain.CommentType;
import kr.co.webcash.domain.Guestbook;
import kr.co.webcash.domain.Post;

@Repository
public class GuestbookRepositoryImpl implements GuestbookRepository{

	@Autowired SqlMapClientTemplate template;
	@Autowired CommentRepository commentRepository;
	
	private void addComments(String blogId, Guestbook guestbook) {
		if(guestbook != null){
			guestbook.setCommentList(commentRepository.findAllByBlogIdAndTargetIdAndType(blogId, guestbook.getId(), CommentType.guestbook));
		}
	}	
	private void addComments(String blogId, List<Guestbook> guestbookList){
		for(Guestbook guestbook : guestbookList){
			addComments(blogId, guestbook);
		}
	}	
	@Override
	public void insert(Guestbook guestbook) {
		template.insert("Guestbook.insert",guestbook);
	}
	@Override
	public Guestbook findLastGuestbookByBlogId(String blogId) {
		Guestbook guestbook = (Guestbook) template.queryForObject("Guestbook.findLastGuestbookByBlogId", blogId);
		addComments(blogId, guestbook);
		
		return guestbook;
	}
	@Override
	public List<Guestbook> findAllByBlogId(String blogId) {
		List<Guestbook> guestbookList = template.queryForList("Guestbook.findAllByBlogId", blogId);
		addComments(blogId, guestbookList);
		
		return guestbookList;
	}
	@Override
	public Guestbook findByIdAndBlogId(String id, String blogId) {
		Map<String, String> param = new HashMap<String,String>();
		param.put("id", id);
		param.put("blogId", blogId);
		Guestbook guestbook = (Guestbook)template.queryForObject("Guestbook.findByIdAndBlogId",param);
		addComments(blogId, guestbook);
		
		return guestbook;
	}
	@Override
	public void update(Guestbook guestbook) {
		template.update("Guestbook.update", guestbook);
	}
	@Override
	public void delete(Guestbook guestbook) {
		template.delete("Guestbook.delete", guestbook);
	}

}
