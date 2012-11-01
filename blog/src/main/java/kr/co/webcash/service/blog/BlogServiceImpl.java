package kr.co.webcash.service.blog;

import java.util.Date;
import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.BlogVisitHistory;
import kr.co.webcash.domain.User;
import kr.co.webcash.repository.BlogRepository;
import kr.co.webcash.repository.CategoryRepository;
import kr.co.webcash.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

@Service("blogServiceCore")
public class BlogServiceImpl implements BlogService {
	
	@Autowired private BlogRepository blogRepository;
	@Autowired private BlogVisitHistoryService blogVisitHistoryService;
	@Autowired private CategoryService categoryService;
	@Autowired private CategoryRepository categoryRepository;
	
	@Autowired PlatformTransactionManager transactionManager;
	
	@Override
	public void createBlog(Blog blog) {
		this.save(blog);
		
		categoryService.saveDefault(blog);
	}
	
	private void save(Blog blog){
		blogRepository.create(blog);
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
