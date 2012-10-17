package kr.co.webcash.service;

import kr.co.webcash.domain.Search;

public interface SearchService {

	Search findAllByQuery(String query);

}
