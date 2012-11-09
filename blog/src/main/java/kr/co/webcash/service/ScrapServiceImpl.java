package kr.co.webcash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Scrap;
import kr.co.webcash.repository.ScrapRepository;

@Service
public class ScrapServiceImpl implements ScrapService{
	
	@Autowired private ScrapRepository scrapRepository;

	@Override
	public List<Scrap> listAllByScrappedBlog(Blog blog) {
		return scrapRepository.findAllByScrappedBlogId(blog.getId());
	}

}
