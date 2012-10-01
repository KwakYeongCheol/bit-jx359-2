package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Guestbook;

public interface GuestbookService {

	void write(Guestbook guestbook);

	long findLastIdByBlogId(String blogId);

	List<Guestbook> listByBlogId(String blogId);

	Guestbook findByIdAndBlogId(long id, String blogId);

	void update(Guestbook guestbook);

	void delete(Guestbook guestbook);
	
}
