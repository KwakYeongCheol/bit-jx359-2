package kr.co.webcash.repository.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.scrap.Scrap;
import kr.co.webcash.repository.BlogRepository;
import kr.co.webcash.repository.PostMetadataRepository;
import kr.co.webcash.repository.PostRevisionRepository;
import kr.co.webcash.repository.ScrapRepository;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScrapRepositoryImpl implements ScrapRepository{
	@Autowired private SqlSession sqlSession;
	
	@Autowired private BlogRepository blogRepository;
	
	@Autowired private PostRevisionRepository postRevisionRepository;
	
	@Autowired private PostMetadataRepository postMetadataRepository;
	
	@Override
	public void insert(Scrap scrap) {
		sqlSession.insert("Scrap.insert", scrap);
	}
	
	@Override
	public void deleteFromPostId(long postId) {
		sqlSession.delete("Scrap.deleteFromPostId", postId);
	}
	
	private List<Scrap> wrap(List<Scrap> scrapList){
		for(Scrap scrap : scrapList){
			wrap(scrap);
		}
		
		return scrapList;
	}
	
	private Scrap wrap(Scrap scrap){
		if(scrap != null){
			addPostRevisionHistory(scrap);
			addPostMetadata(scrap);
		}
		return scrap;
	}
	
	private void addPostRevisionHistory(Scrap scrap) {
		Post targetPost = scrap.getTarget().getPost();
		targetPost.setPostRevisionList(postRevisionRepository.findAllByPost(targetPost));
	}
	
	private void addPostMetadata(Scrap scrap) {
		Post targetPost = scrap.getTarget().getPost();
		targetPost.setPostMetadata(postMetadataRepository.findByPostId(targetPost.getId()));
	}

	@Override
	public Scrap findByPostIdAndTargetPostIdAndTargetPostRevisionId(long postId, long targetPostId, long targetPostRevisionId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("postId", postId);
		params.put("targetPostId", targetPostId);
		params.put("targetPostRevisionId", targetPostRevisionId);
		
		return sqlSession.<Scrap>selectOne("Scrap.findByPostIdAndTargetPostIdAndTargetPostRevisionId", params);
	}

	@Override
	public List<Scrap> findAllByPostId(long postId) {
		return wrap(sqlSession.<Scrap>selectList("Scrap.findAllByPostId", postId));
	}
	
	@Override
	public List<Scrap> findAllByTargetBlogId(String blogId) {
		return wrap(sqlSession.<Scrap>selectList("Scrap.findAllByTargetBlogId", blogId));
	}

	@Override
	public int countByBlogId(String blogId) {
		return sqlSession.<Integer>selectOne("Scrap.countByBlogId", blogId);
	}
	
	@Override
	public List<Scrap> findAllByTargetPostId(long targetPostId) {
		return wrap(sqlSession.<Scrap>selectList("Scrap.findAllByTargetPostId", targetPostId));
	}

	@Override
	public List<Scrap> findAllByBlogIdAndPage(String blogId, int offset, int limit) {
		return wrap(sqlSession.<Scrap>selectList("Scrap.findAllByBlogId", blogId, new RowBounds(offset, limit)));
	}
}
