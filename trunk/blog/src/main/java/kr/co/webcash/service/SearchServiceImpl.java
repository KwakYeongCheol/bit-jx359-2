package kr.co.webcash.service;

import kr.co.webcash.domain.Search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired private PostService postService;

	@Override
	public Search findAllByQuery(String query) {
		
		Search search = new Search();
		
		search.setPostList(postService.search(query));
		
		return search;
	}
	
	

}
