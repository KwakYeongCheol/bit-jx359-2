package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;


import kr.co.webcash.domain.Guestbook;

@Repository
public class GuestbookRepositoryImpl implements GuestbookRepository{

	@Autowired SqlMapClientTemplate template;
	@Override
	public void insert(Guestbook guestbook) {
		template.insert("Guestbook.insert",guestbook);
	}
	@Override
	public Guestbook findLastGuestbookByBlogId(String blogId) {
		return (Guestbook) template.queryForObject("Guestbook.findLastGuestbookByBlogId", blogId);
	}
	@Override
	public List<Guestbook> findAllByBlogId(String blogId) {
		return template.queryForList("Guestbook.findAllByBlogId", blogId);
	}
	@Override
	public Guestbook findByIdAndBlogId(String id, String blogId) {
		Map<String, String> param = new HashMap<String,String>();
		param.put("id", id);
		param.put("blogId", blogId);
		return (Guestbook)template.queryForObject("Guestbook.findByIdAndBlogId",param);
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
