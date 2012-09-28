package kr.co.webcash.service;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Category;
import kr.co.webcash.repository.BlogRepository;
import kr.co.webcash.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired private BlogRepository blogRepository;
	@Autowired private CategoryRepository categoryRepository;
	
	@Override
	public void createBlog(Blog blog) {
		blogRepository.create(blog);
		Category category = new Category();
		category.setId(1);
		category.setBlog(blog);
		category.setTitle("내 글");
		
		categoryRepository.insert(category);
	}

	@Override
	public Blog findByUserLoginId(String loginId) {
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

	@Override
	public Blog findById(String blogId) {
		return blogRepository.findById(blogId);
	}

}
