package kr.co.webcash.service;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.Search;

public interface SearchService {
	int countByQuery(String query);
	
	Search findAllByQueryAndPage(String query, Page page);
}
