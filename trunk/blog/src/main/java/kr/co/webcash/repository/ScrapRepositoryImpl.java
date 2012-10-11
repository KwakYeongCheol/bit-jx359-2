package kr.co.webcash.repository;

import kr.co.webcash.domain.Scrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScrapRepositoryImpl implements ScrapRepository{
	@Autowired private SqlMapClientTemplate template;
	
	@Autowired private BlogRepository blogRepository;
	
	@Override
	public Scrap findByPostId(long postId) {
		Scrap scrap = (Scrap) template.queryForObject("Scrap.findByPostId", postId);
		
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
