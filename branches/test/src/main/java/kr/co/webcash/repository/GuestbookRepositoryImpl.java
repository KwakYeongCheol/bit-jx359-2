package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.CommentType;
import kr.co.webcash.domain.Guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GuestbookRepositoryImpl implements GuestbookRepository{

	@Autowired SqlMapClientTemplate template;
	@Autowired CommentRepository commentRepository;
	
	@Override
	public void insert(Guestbook guestbook) {
		template.insert("Guestbook.insert",guestbook);
	}
	
	@Override
	public void update(Guestbook guestbook) {
		template.update("Guestbook.update", guestbook);
	}
	@Override
	public void delete(Guestbook guestbook) {
		template.delete("Guestbook.delete", guestbook);
	}
	
	@Override
	public Guestbook findLastGuestbookByBlogId(String blogId) {
		Guestbook guestbook = (Guestbook) template.queryForObject("Guestbook.findLastGuestbookByBlogId", blogId);
		addComments(guestbook);
		
		return guestbook;
	}
	@Override
	public List<Guestbook> findAllByBlogId(String blogId) {
		List<Guestbook> guestbookList = template.queryForList("Guestbook.findAllByBlogId", blogId);
		addComments(guestbookList);
		
		return guestbookList;
	}
	@Override
	public Guestbook findByBlogIdAndDisplayId(String blogId, long displayId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("blogId", blogId);
		param.put("displayId", displayId);
		
		Guestbook guestbook = (Guestbook)template.queryForObject("Guestbook.findByBlogIdAndDisplayId", param);
		addComments(guestbook);
		
		return guestbook;
	}
	
	@Override
	public Guestbook findLast() {
		Guestbook guestbook = (Guestbook) template.queryForObject("Guestbook.findLast");
		
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
