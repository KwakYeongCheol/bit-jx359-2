package kr.co.webcash.service;

import java.util.Date;
import java.util.List;

import kr.co.webcash.domain.Guestbook;
import kr.co.webcash.repository.GuestbookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestbookServiceImpl implements GuestbookService{
	@Autowired GuestbookRepository guestbookRepository;

	@Override
	public void save(Guestbook guestbook) {
		Guestbook lastGuestbook = guestbookRepository.findLast();
		
		if(lastGuestbook == null){
			guestbook.setId(1);
		}else{
			guestbook.setId(lastGuestbook.getId() + 1);
		}
		
		guestbook.setDisplayId(findLastIdByBlogId(guestbook.getBlog().getId()) + 1);
		guestbook.setDateCreated(new Date());
		
		guestbookRepository.insert(guestbook);
	}

	@Override
	public long findLastIdByBlogId(String blogId) {
		Guestbook guestbook = guestbookRepository.findLastGuestbookByBlogId(blogId);
		if(guestbook == null)		return 0;
		
		return guestbook.getId();
	}

	@Override
	public List<Guestbook> listByBlogId(String blogId) {
		return guestbookRepository.findAllByBlogId(blogId);
	}

	@Override
	public Guestbook findByBlogIdAndDisplayId(String blogId, long displayId) {
		return guestbookRepository.findByBlogIdAndDisplayId(blogId, displayId);
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
