package kr.co.webcash.repository;

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

}
