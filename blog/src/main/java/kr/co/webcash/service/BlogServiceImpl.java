package kr.co.webcash.service;

import java.util.Date;
import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.BlogVisitHistory;
import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.User;
import kr.co.webcash.repository.BlogRepository;
import kr.co.webcash.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired private BlogRepository blogRepository;
	@Autowired private BlogVisitHistoryService blogVisitHistoryService;
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
	public List<Blog> findAllByUserLoginId(String loginId) {
		return blogRepository.findAllByUserLoginId(loginId);
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

	@Override
	public List<Blog> findAll() {
		return blogRepository.findAll();
	}

	@Override
	public void addVisitCount(Blog blog) {
		BlogVisitHistory blogVisitHistory = new BlogVisitHistory(blog, new Date(System.currentTimeMillis()));
		blogVisitHistoryService.save(blogVisitHistory);
		
		blog.setTodayCount(blogVisitHistoryService.countToday(blog));
		blog.setTotalCount(blog.getTotalCount() + 1);
		
		blogRepository.updateTotalCount(blog);
	}
}
