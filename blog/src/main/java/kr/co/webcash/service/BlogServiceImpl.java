package kr.co.webcash.service;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.User;
import kr.co.webcash.repository.BlogRepository;
import kr.co.webcash.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired private BlogRepository blogRepository;
	@Autowired private CategoryService categoryService;
	@Autowired private CategoryRepository categoryRepository;
	
	@Override
	public void createBlog(Blog blog) {
		blogRepository.create(blog);
		Category category = new Category();
		category.setBlog(blog);
		category.setDisplayId(1);
		category.setTitle("분류없음");
		
		categoryService.save(category);
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

	@Override
	public boolean isAdmin(String blogId, User user) {
		if(notNull(blogId) && notNull(user) && notNull(user.getLoginId())){
			Blog findBlog = blogRepository.findById(blogId);
			
			if(notNull(findBlog)){
				if(user.getLoginId().equals(findBlog.getOwner()))		return true;
			}
		}
		
		return false;
	}
	
	private boolean notNull(Object obj){
		if(obj != null){
			return true;
		}
		
		return false;
	}
}
