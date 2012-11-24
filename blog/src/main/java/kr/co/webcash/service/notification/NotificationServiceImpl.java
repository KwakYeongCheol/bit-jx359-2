package kr.co.webcash.service.notification;

import java.util.List;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.notification.Notificable;
import kr.co.webcash.domain.notification.Notification;
import kr.co.webcash.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired private NotificationRepository notificationRepository;

	@Override
	public boolean sendNotification(Notificable notificable) {
		return sendNotification(notificable, true);
	}
	
	@Override
	public boolean sendNotification(Notificable notificable, boolean isPublic) {
		Notification notification = new Notification();
		
		notification.setBlog(notificable.getNotificationBlog());
		notification.setContents(notificable.getNotificationContents());
		notification.setIsPublic(isPublic);
		
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
	public boolean delete(Notification notification) {
		return notificationRepository.delete(notification);
	}


	@Override
	public List<Notification> listByBlog(Blog blog) {
		return notificationRepository.findAllByBlogId(blog.getId());
	}

	@Override
	public List<Notification> listByBlog(Blog blog, int pageNumber) {
		return listByBlogIdAndPageNumberAndPageSize(blog.getId(), pageNumber, 10);
	}
	
	@Override
	public List<Notification> listByBlogIdAndPageNumberAndPageSize(String blogId, int pageNumber, int pageSize) {
		return notificationRepository.findAllByBlogIdAndPage(blogId, pageNumber, pageSize);
	}
	

	@Override
	public List<Notification> listPublicByBlogAndPage(Blog blog, int pageNumber, int pageSize) {
		return notificationRepository.findAllPublicByBlogIdAndPage(blog.getId(), pageNumber, pageSize);
	}

	@Override
	public int countByBlogId(String blogId) {
		return notificationRepository.countByBlogId(blogId);
	}
}
