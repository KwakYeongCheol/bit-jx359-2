package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Visitor;

public interface VisitorService {

	void write(Visitor visitor);

	int findLastIdByBlogId(String blogId);

	List<Visitor> listByBlogId(String blogId);

	Visitor findByIdAndBlogId(String id, String blogId);

	void update(Visitor visitor);
	
}
