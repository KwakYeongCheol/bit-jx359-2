package kr.co.webcash.service.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.User;
import kr.co.webcash.domain.notification.Notificable;
import kr.co.webcash.domain.notification.Notification;
import kr.co.webcash.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired private NotificationRepository notificationRepository;

	@Override
	public boolean sendNotification(Notificable notificable) {
		Notification notification = new Notification();
		
		notification.setBlog(notificable.getNotificationBlog());
		notification.setUri(notificable.getNotificationURI());
		notification.setContents(notificable.getNotificationContents());
		
		save(notification);
		
		return false;
	}
	
	@Override
	public void save(Notification notification) {
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

	@Override
	public List<Notification> listByBlog(Blog blog, int pageNumber) {
		int pageSize = 10;
		return notificationRepository.findAllByBlogIdAndPage(blog.getId(), pageNumber, pageSize);
	}


}
