package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Guestbook;

public interface GuestbookRepository {
	void insert(Guestbook guestbook);
	void update(Guestbook guestbook);
	void delete(Guestbook guestbook);

	Guestbook findLastGuestbookByBlogId(String blogId);

	List<Guestbook> findAllByBlogId(String blogId);

	Guestbook findByBlogIdAndDisplayId(String blogId, long displayId);
	Guestbook findLast();
}
