package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Scrap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScrapRepositoryImpl implements ScrapRepository{
	@Autowired private SqlSession sqlSession;
	
	@Autowired private BlogRepository blogRepository;
	
	@Override
	public List<Scrap> findAllByPostId(long postId) {
		List<Scrap> scrapList = sqlSession.selectList("Scrap.findAllByPostId", postId);
		
		addMoreInfo(scrapList);
		
		return scrapList;
	}
	
	private void addMoreInfo(List<Scrap> scrapList){
		for(Scrap scrap : scrapList){
			addMoreInfo(scrap);
		}
	}
	
	private void addMoreInfo(Scrap scrap){
		if(scrap != null){
			scrap.setScrappedBlog(blogRepository.findById(scrap.getScrappedBlog().getId()));
		}
	}

	@Override
	public void insert(Scrap scrap) {
		sqlSession.insert("Scrap.insert", scrap);
	}

	@Override
	public Scrap findByPostIdAndTargetBlogIdAndTargetPostDisplayId(long postId, String targetBlogId, long targetPostDisplayId) {
		Map params = new HashMap();
		params.put("postId", postId);
		params.put("scrappedBlogId", targetBlogId);
		params.put("scrappedPostDisplayId", targetPostDisplayId);
		
		Scrap scrap = (Scrap) sqlSession.selectOne("Scrap.findByPostIdAndScrappedBlogIdAndScrappedPostDisplayId", params);
		addMoreInfo(scrap);
		
		return scrap;
	}

}
