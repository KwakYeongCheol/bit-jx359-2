package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Visitor;
import kr.co.webcash.repository.VisitorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl implements VisitorService{
	
	@Autowired VisitorRepository visitorRepository;

	@Override
	public void write(Visitor visitor) {
		visitorRepository.insert(visitor);
		
	}

	@Override
	public int findLastIdByBlogId(String blogId) {
		Visitor visitor = visitorRepository.findLastVisitorByBlogId(blogId);
		if(visitor == null)		return 0;
		
		return Integer.parseInt(visitor.getId());
	}

	@Override
	public List<Visitor> listByBlogId(String blogId) {
		return visitorRepository.findAllByBlogId(blogId);
	}

	@Override
	public Visitor findByIdAndBlogId(String id, String blogId) {
		return visitorRepository.findByIdAndBlogId(id, blogId);
	}

	@Override
	public void update(Visitor visitor) {
		visitorRepository.update(visitor);
	}

	@Override
	public void delete(Visitor visitor) {
		visitorRepository.delete(visitor);
	}

}
