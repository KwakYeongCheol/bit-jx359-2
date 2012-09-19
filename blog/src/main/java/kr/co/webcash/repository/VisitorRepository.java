package kr.co.webcash.repository;

import kr.co.webcash.domain.Visitor;

public interface VisitorRepository {

	void insert(Visitor visitor);

	Visitor findLastVisitorByBlogId(String blogId);


}
