package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Guestbook;
import kr.co.webcash.domain.Page;

public interface GuestbookRepository {
	void insert(Guestbook guestbook);
	void update(Guestbook guestbook);
	void delete(Guestbook guestbook);

	Guestbook findLastGuestbookByBlogId(String blogId);

	List<Guestbook> findAllByBlogId(String blogId);
	List<Guestbook> findAllByBlogIdAndPage(String blogId, Page page);
	List<Guestbook> findAllByBlogIdAndPageNumberAndPageSize(String blogId, int pageNumber, int pageSize);

	Guestbook findByBlogIdAndDisplayId(String blogId, long displayId);
	Guestbook findLast();
	
	int countByBlogId(String blogId);
}
