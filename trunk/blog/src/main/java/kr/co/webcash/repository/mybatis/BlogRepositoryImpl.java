package kr.co.webcash.repository.mybatis;

import java.util.List;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.repository.BlogRepository;
import kr.co.webcash.repository.CommentRepository;
import kr.co.webcash.repository.GuestbookRepository;
import kr.co.webcash.repository.PostRepository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepositoryImpl implements BlogRepository{
	@Autowired private SqlSession sqlSession;
	
	@Autowired private PostRepository postRepository;
	@Autowired private GuestbookRepository guestbookRepository;
	@Autowired private CommentRepository commentRepository;
	
	@Override
	public void create(Blog blog) {
		try{
			sqlSession.insert("Blog.create", blog);
		}catch(DataAccessException e){
			throw e;
		}
	}

	@Override
	public void update(Blog blog) {
		sqlSession.update("Blog.update", blog);
	}
	
	@Override
	public void updateTotalCount(Blog blog) {
		sqlSession.update("Blog.updateTotalCount", blog);
	}
	
	public List<Blog> wrap(List<Blog> blogList){
		for(Blog blog : blogList){
			wrap(blog);
		}
		return blogList;
	}
	
	public Blog wrap(Blog blog){
		if(blog != null){
			addTotalPostCount(blog);
			addTotalGuestbookCount(blog);
			addTotalCommentCount(blog);
		}
		
		return blog;
	}
	
	private void addTotalPostCount(Blog blog) {
		blog.setTotalPostCount(postRepository.countByBlogId(blog.getId()));
	}

	private void addTotalGuestbookCount(Blog blog) {
		blog.setTotalGuestbookCount(guestbookRepository.countByBlogId(blog.getId()));
	}

	private void addTotalCommentCount(Blog blog) {
		blog.setTotalCommentCount(commentRepository.countByBlogId(blog.getId()));
	}

	@Override
	public Blog findById(String id) {
		return wrap(sqlSession.<Blog>selectOne("Blog.findById", id));
	}
	
	@Override
	public List<Blog> findAll() {
		return wrap(sqlSession.<Blog>selectList("Blog.findAll"));
	}
	
	@Override
	public List<Blog> findAllByUserLoginId(String loginId) {
		return wrap(sqlSession.<Blog>selectList("Blog.findAllByUserLoginId", loginId));
	}
}
