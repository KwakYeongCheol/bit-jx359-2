package kr.co.webcash.service.blog;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service("blogService")
public class BlogServiceTx implements BlogService {
	
	@Autowired
	@Qualifier("blogServiceCore") 
	private BlogService blogService;
	
	@Autowired private PlatformTransactionManager transactionManager;
	
	@Override
	public void createBlog(Blog blog) {
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			blogService.createBlog(blog);
			this.transactionManager.commit(txStatus);
		}catch(RuntimeException e){
			this.transactionManager.rollback(txStatus);
		}
	}
	
	@Override
	public List<Blog> findAllByUserLoginId(String loginId) {
		return blogService.findAllByUserLoginId(loginId);
	}

	@Override
	public boolean isExist(String id) {
		return this.blogService.isExist(id);
	}

	@Override
	public void modify(Blog blog) {
		this.blogService.modify(blog);
	}

	@Override
	public Blog findById(String blogId) {
		return this.blogService.findById(blogId);
	}

	@Override
	public boolean isAdmin(String blogId, User user) {
		return this.blogService.isAdmin(blogId, user);
	}
	
	@Override
	public List<Blog> findAll() {
		return this.blogService.findAll();
	}

	@Override
	public void addVisitCount(Blog blog) {
		this.blogService.addVisitCount(blog);
	}
}
