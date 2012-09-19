package kr.co.webcash.service;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired private BlogRepository blogRepository;

	@Override
	public void createBlog(Blog blog) {
		// TODO Auto-generated method stub
		blogRepository.create(blog);
	}

	@Override
	public Blog findByUserLoginId(String loginId) {
		// TODO Auto-generated method stub
		return blogRepository.findByUserLoginId(loginId);
	}

	@Override
	public boolean isExist(String id) {
		
		if(blogRepository.findById(id) != null) return true;
		
		return false;
	}

	@Override
	public void modify(Blog blog) {
		blogRepository.update(blog);
	}

}
