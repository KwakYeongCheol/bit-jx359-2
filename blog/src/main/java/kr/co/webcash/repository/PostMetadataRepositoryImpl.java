package kr.co.webcash.repository;

import kr.co.webcash.domain.post.PostMetadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostMetadataRepositoryImpl implements PostMetadataRepository{
	
	@Autowired private SqlMapClientTemplate template;

	@Override
	public PostMetadata findByPostId(long postId) {
		return (PostMetadata) template.queryForObject("PostMetadata.findByPostId", postId);
	}

}
