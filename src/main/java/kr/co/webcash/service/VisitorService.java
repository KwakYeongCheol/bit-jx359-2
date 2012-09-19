package kr.co.webcash.service;

import kr.co.webcash.domain.Visitor;

public interface VisitorService {

	void write(Visitor visitor);

	int findLastIdByBlogId(String blogId);
	
}
