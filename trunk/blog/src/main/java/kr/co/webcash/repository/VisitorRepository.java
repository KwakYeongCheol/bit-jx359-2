package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Visitor;

public interface VisitorRepository {

	void insert(Visitor visitor);

	Visitor findLastVisitorByBlogId(String blogId);

	List<Visitor> findAllByBlogId(String blogId);

	Visitor findByIdAndBlogId(String id, String blogId);

	void update(Visitor visitor);

	void delete(Visitor visitor);


}
