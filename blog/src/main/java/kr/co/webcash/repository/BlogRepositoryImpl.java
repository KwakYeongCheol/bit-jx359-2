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

}
