package kr.co.webcash.service.blog;

import kr.co.webcash.domain.blog.BlogWidget;
import kr.co.webcash.repository.BlogWidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogWidgetServiceImpl implements BlogWidgetService{
	
	@Autowired private BlogWidgetRepository blogWidgetRepository;

	@Override
	public void save(BlogWidget blogWidget) {
		if(blogWidgetRepository.findByBlogId(blogWidget.getBlog().getId()) == null){
			blogWidgetRepository.insert(blogWidget);
		}else{
			blogWidgetRepository.update(blogWidget);
		}
	}
}
