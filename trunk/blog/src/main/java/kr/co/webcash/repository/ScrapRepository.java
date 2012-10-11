package kr.co.webcash.repository;

import kr.co.webcash.domain.Scrap;

public interface ScrapRepository {

	Scrap findByPostId(long postId);

	void insert(Scrap scrap);

}
