package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import kr.co.webcash.domain.Post;

@Repository
public class PostRepositoryImpl implements PostRepository {
	
	@Autowired SqlMapClientTemplate template;

	@Override
	public Post findLastPostByBlogId(String blogId) {
		return (Post) template.queryForObject("Post.findLastPostByBlogId", blogId);
	}

	@Override
	public void insert(Post post) {
		template.insert("Post.insert", post);
	}

	@Override
	public List<Post> findAllByBlogId(String blogId) {
		return template.queryForList("Post.findAllByBlogId", blogId);
	}

	@Override
	public Post findByIdAndBlogId(String id, String blogId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", id);
		param.put("blogId", blogId);
		return (Post) template.queryForObject("Post.findByIdAndBlogId", param );
	}

	@Override
	public void update(Post post) {
		template.update("Post.update", post);
		
	}

	@Override
	public void delete(Post post) {
		template.delete("Post.delete", post);
	}

	@Override
	public List<Post> findAllByBlogIdAndCategoryId(String blogId, String categoryId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("blogId", blogId);
		param.put("categoryId", categoryId);
		return template.queryForList("Post.findAllByBlogIdAndCategoryId", param );
	}

}
