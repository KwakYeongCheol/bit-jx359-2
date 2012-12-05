package kr.co.webcash.repository.mybatis;

import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.PostMetadata;
import kr.co.webcash.repository.PostMetadataRepository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostMetadataRepositoryImpl implements PostMetadataRepository{
	
	@Autowired private SqlSession sqlSession;

	@Override
	public void insert(PostMetadata metadata) {
		sqlSession.insert("PostMetadata.insert", metadata);
	}
	
	@Override
	public PostMetadata findByPostId(long postId) {
		return (PostMetadata) sqlSession.selectOne("PostMetadata.findByPostId", postId);
	}

	@Override
	public void update(PostMetadata postMetadata) {
		sqlSession.update("PostMetadata.update", postMetadata);
	}

	@Override
	public void delete(Post post) {
		sqlSession.delete("PostMetadata.deleteByPostId", post.getId());
	}
}
