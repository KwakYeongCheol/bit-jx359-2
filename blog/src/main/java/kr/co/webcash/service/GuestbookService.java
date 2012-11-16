package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Guestbook;
import kr.co.webcash.domain.Page;

public interface GuestbookService {
	void save(Guestbook guestbook);
	void update(Guestbook guestbook);
	void delete(Guestbook guestbook);

	List<Guestbook> listByBlogId(String blogId);
	List<Guestbook> listByBlogIdAndPage(String blogId, Page page);
	List<Guestbook> listByBlogIdAndPageNumberAndPageSize(String blogId, int pageNumber, int pageSize);

	long findLastIdByBlogId(String blogId);
	Guestbook findByBlogIdAndDisplayId(String blogId, long displayId);
	
	int countByBlogId(String blogId);
}
