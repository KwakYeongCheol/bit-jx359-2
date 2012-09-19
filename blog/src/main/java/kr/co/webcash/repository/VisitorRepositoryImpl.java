package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;


import kr.co.webcash.domain.Visitor;

@Repository
public class VisitorRepositoryImpl implements VisitorRepository{

	@Autowired SqlMapClientTemplate template;
	@Override
	public void insert(Visitor visitor) {
		template.insert("Visitor.insert",visitor);
	}
	@Override
	public Visitor findLastVisitorByBlogId(String blogId) {
		return (Visitor) template.queryForObject("Visitor.findLastVisitorByBlogId", blogId);
	}
	@Override
	public List<Visitor> findAllByBlogId(String blogId) {
		return template.queryForList("Visitor.findAllByBlogId", blogId);
	}
	@Override
	public Visitor findByIdAndBlogId(String id, String blogId) {
		Map<String, String> param = new HashMap<String,String>();
		param.put("id", id);
		param.put("blogId", blogId);
		return (Visitor)template.queryForObject("Visitor.findByIdAndBlogId",param);
	}
	@Override
	public void update(Visitor visitor) {
		template.update("Visitor.update", visitor);
	}

}
