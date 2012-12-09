package kr.co.webcash.repository.mybatis;

import java.util.List;

import kr.co.webcash.domain.blog.BlogTag;
import kr.co.webcash.domain.post.PostTag;
import kr.co.webcash.repository.PostTagRepository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostTagRepositoryImpl implements PostTagRepository {
	
	@Autowired private SqlSession session;

	@Override
	public boolean insert(PostTag postTag) {
		session.insert("PostTag.insert", postTag);
		return true;
	}

	@Override
	public List<PostTag> findAllByPostId(long postId) {
		return session.selectList("PostTag.findAllByPostId", postId);
	}

	@Override
	public void deleteFromPostId(long postId) {
		session.delete("PostTag.deleteFromPostId", postId);
	}

	@Override
	public List<BlogTag> findAllBlogTagByBlogId(String blogId) {
		return session.selectList("PostTag.findAllBlogTagByBlogId", blogId);
	}

}
