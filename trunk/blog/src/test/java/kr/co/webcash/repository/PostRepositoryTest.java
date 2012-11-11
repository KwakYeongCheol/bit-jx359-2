package kr.co.webcash.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.service.blog.BlogService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-servlet-context.xml")
public class PostRepositoryTest {
	
	@Autowired PlatformTransactionManager transactionManager;
	
	@Autowired BlogRepository blogRepository;
	
	private Blog blog1;
	private Blog blog2;
	private Blog blog3;
	
	@Before
	public void before(){
		blog1 = new Blog("kwakyc87", "곽군의 블로그", "1", new Date(System.currentTimeMillis()), 0);
		blog2 = new Blog("kwakyc123", "곽군의 블로그", "1", new Date(System.currentTimeMillis()), 0);
		blog3 = new Blog("kwakyc555", "곽군의 블로그", "1", new Date(System.currentTimeMillis()), 0);
	}
	
	@Test
	public void canTransaction(){
		List<Blog> findAll = blogRepository.findAll();
		final int startSize = findAll.size();
		
		blogRepository.create(blog1);
		assertThat(blogRepository.findAll().size(), is(startSize + 1));
		
		new TransactionTemplate(transactionManager).execute(
			new TransactionCallback<Object>() {
				@Override
				public Object doInTransaction(TransactionStatus status) {
					status.setRollbackOnly();
					
					blogRepository.create(blog2);
					assertThat(blogRepository.findAll().size(), is(startSize + 2));
					
					blogRepository.create(blog3);
					assertThat(blogRepository.findAll().size(), is(startSize + 3));

					return null;
				}
		});
		
		assertThat(blogRepository.findAll().size(), is(startSize + 1));
	}
	
	@Test
	@Transactional
	public void withTransaction(){
		blogRepository.create(blog2);
		System.out.println(blogRepository.findAll());
	}
}
