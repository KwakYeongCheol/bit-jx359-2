package kr.co.webcash.repository;

import java.util.Map;

import kr.co.webcash.domain.BlogVisitHistory;

public interface BlogVisitHistoryRepository {

	void insert(BlogVisitHistory blogVisitHistory);

	long countByBlogIdAndFromStartDateToEndDate(Map params);

	BlogVisitHistory findByBlogIdAndConnectIPAddressAndFromStartDateToEndDate(Map<String, Object> params);

}
