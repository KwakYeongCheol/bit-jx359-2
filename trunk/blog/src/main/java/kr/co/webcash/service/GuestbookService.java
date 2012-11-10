package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Guestbook;

public interface GuestbookService {
	void save(Guestbook guestbook);
	void update(Guestbook guestbook);
	void delete(Guestbook guestbook);

	List<Guestbook> listByBlogId(String blogId);

	long findLastIdByBlogId(String blogId);
	Guestbook findByBlogIdAndDisplayId(String blogId, long displayId);
}
