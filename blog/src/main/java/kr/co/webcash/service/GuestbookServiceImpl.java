package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Guestbook;
import kr.co.webcash.repository.GuestbookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestbookServiceImpl implements GuestbookService{
	
	@Autowired GuestbookRepository guestbookRepository;

	@Override
	public void write(Guestbook guestbook) {
		guestbookRepository.insert(guestbook);
		
	}

	@Override
	public int findLastIdByBlogId(String blogId) {
		Guestbook visitor = guestbookRepository.findLastGuestbookByBlogId(blogId);
		if(visitor == null)		return 0;
		
		return Integer.parseInt(visitor.getId());
	}

	@Override
	public List<Guestbook> listByBlogId(String blogId) {
		return guestbookRepository.findAllByBlogId(blogId);
	}

	@Override
	public Guestbook findByIdAndBlogId(String id, String blogId) {
		return guestbookRepository.findByIdAndBlogId(id, blogId);
	}

	@Override
	public void update(Guestbook guestbook) {
		guestbookRepository.update(guestbook);
	}

	@Override
	public void delete(Guestbook guestbook) {
		guestbookRepository.delete(guestbook);
	}

}
