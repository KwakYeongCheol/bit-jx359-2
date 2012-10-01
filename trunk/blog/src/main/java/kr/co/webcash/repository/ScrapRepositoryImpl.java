package kr.co.webcash.repository;

import java.util.Map;

import kr.co.webcash.domain.Scrap;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScrapRepositoryImpl implements ScrapRepository{
	@Autowired private SqlMapClientTemplate template;
	
	@Autowired private BlogRepository blogRepository;
	
	@Override
	public Scrap findByBlogIdAndPostId(String blogId, long postId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("postId", postId);
		Scrap scrap = (Scrap) template.queryForObject("Scrap.findByBlogIdAndPostId",params);
		
		if(scrap != null){
			scrap.setScrappedBlog(blogRepository.findById(scrap.getScrappedBlog().getId()));
		}
		
		return scrap;
	}

	@Override
	public void insert(Scrap scrap) {
		template.insert("Scrap.insert", scrap);
	}

}
