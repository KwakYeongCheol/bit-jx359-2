package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Guestbook;

public interface GuestbookRepository {

	void insert(Guestbook visitor);

	Guestbook findLastGuestbookByBlogId(String blogId);

	List<Guestbook> findAllByBlogId(String blogId);

	Guestbook findByIdAndBlogId(long id, String blogId);

	void update(Guestbook visitor);

	void delete(Guestbook visitor);


}
