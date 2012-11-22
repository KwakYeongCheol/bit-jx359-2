package kr.co.webcash.service;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.Search;
import kr.co.webcash.service.post.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired private PostService postService;

	@Override
	public Search findAllByQueryAndPage(String query, Page page) {
		
		Search search = new Search();
		
		search.setPostList(postService.search(query, page));
		
		return search;
	}

	@Override
	public int countByQuery(String query) {
		return postService.countByQuery(query);
	}

}
