package kr.co.webcash.service.notification;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.notification.Notificable;
import kr.co.webcash.domain.notification.Notification;

public interface NotificationService {

	void save(Notification notification);

	List<Notification> listByBlog(Blog blog);

	List<Notification> listByBlog(Blog blog, int pageNumber);

	boolean sendNotification(Notificable notificable);
}
