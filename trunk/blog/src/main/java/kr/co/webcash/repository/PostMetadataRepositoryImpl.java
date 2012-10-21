package kr.co.webcash.repository;

import kr.co.webcash.domain.post.PostMetadata;

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


}
