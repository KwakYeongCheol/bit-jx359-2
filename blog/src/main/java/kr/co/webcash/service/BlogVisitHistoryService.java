package kr.co.webcash.service;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.BlogVisitHistory;

public interface BlogVisitHistoryService {

	boolean save(BlogVisitHistory blogVisitHistory);

	long countToday(Blog blog);

}
