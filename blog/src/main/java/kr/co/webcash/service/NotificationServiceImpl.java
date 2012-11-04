package kr.co.webcash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Notification;
import kr.co.webcash.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired private NotificationRepository notificationRepository;

	@Override
	public void sendNotification(Notification notification) {
		Notification lastNotification = notificationRepository.findLast();
		
		if(lastNotification == null){
			notification.setId(1);
		}else{
			notification.setId(lastNotification.getId() + 1);
		}
		
		notificationRepository.insert(notification);
	}

	@Override
	public List<Notification> listByBlog(Blog blog) {
		return notificationRepository.findAllByBlogId(blog.getId());
	}

}
