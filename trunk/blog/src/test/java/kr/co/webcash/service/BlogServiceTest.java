package kr.co.webcash.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.service.blog.BlogService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-servlet-context.xml")
public class BlogServiceTest {
	
	@Autowired BlogService blogService;
	
	@Test
	public void createBlogAlreadyExist(){
		List<Blog> blogList = blogService.findAll();
		assertThat(blogList.isEmpty(), is(false));
		
		Blog blog = blogList.get(0);
		blogService.createBlog(blog);
	}
	
	@Test
	public void createBlog(){
		Blog blog = new Blog("test-", "test-Title", "test-owner", new Date(System.currentTimeMillis()), 0);
		blogService.createBlog(blog);
		
		assertNotNull(blogService.findById(blog.getId()));
	}
}
