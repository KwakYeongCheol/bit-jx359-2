package kr.co.webcash.repository;

import kr.co.webcash.domain.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepositoryImpl implements BlogRepository{

	@Autowired SqlMapClientTemplate template;
	
	@Override
	public void create(Blog blog) {
		template.insert("Blog.create", blog);
	}

	@Override
	public Blog findByUserLoginId(String loginId) {
		// TODO Auto-generated method stub
		return (Blog)template.queryForObject("Blog.findByUserLoginId", loginId);
	}

	@Override
	public Blog findById(String id) {
		return (Blog)template.queryForObject("Blog.findById", id);
	}

	@Override
	public void update(Blog blog) {
		template.update("Blog.update", blog);
	}

}
