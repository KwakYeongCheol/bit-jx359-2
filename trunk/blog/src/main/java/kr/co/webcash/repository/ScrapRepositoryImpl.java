package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Scrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScrapRepositoryImpl implements ScrapRepository{
	@Autowired private SqlMapClientTemplate template;
	
	@Autowired private BlogRepository blogRepository;
	
	@Override
	public List<Scrap> findAllByPostId(long postId) {
		List<Scrap> scrapList = template.queryForList("Scrap.findAllByPostId", postId);
		
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
		template.insert("Scrap.insert", scrap);
	}

	@Override
	public Scrap findByPostIdAndTargetBlogIdAndTargetPostId(long postId, String targetBlogId, String targetPostDisplayId) {
		Map params = new HashMap();
		params.put("postId", postId);
		params.put("scrappedBlogId", targetBlogId);
		params.put("scrappedPostId", targetPostDisplayId);
		
		Scrap scrap = (Scrap) template.queryForObject("Scrap.findByPostIdAndScrappedBlogIdAndScrappedPostId", params);
		addMoreInfo(scrap);
		
		return scrap;
	}

}
