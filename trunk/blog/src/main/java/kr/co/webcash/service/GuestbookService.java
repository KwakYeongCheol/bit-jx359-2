package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Guestbook;

public interface GuestbookService {
	void write(Guestbook guestbook);
	void update(Guestbook guestbook);
	void delete(Guestbook guestbook);

	long findLastIdByBlogId(String blogId);

	List<Guestbook> listByBlogId(String blogId);

	Guestbook findByBlogIdAndDisplayId(String blogId, long displayId);
}
