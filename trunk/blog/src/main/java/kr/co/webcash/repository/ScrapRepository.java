package kr.co.webcash.repository;

import kr.co.webcash.domain.Scrap;

public interface ScrapRepository {

	Scrap findByBlogIdAndPostId(String blogId, long postId);

	void insert(Scrap scrap);

}
