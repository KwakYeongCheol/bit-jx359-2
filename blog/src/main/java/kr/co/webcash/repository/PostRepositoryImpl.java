package kr.co.webcash.repository;

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
		System.out.println(post);
		template.insert("Post.insert", post);
	}

}
