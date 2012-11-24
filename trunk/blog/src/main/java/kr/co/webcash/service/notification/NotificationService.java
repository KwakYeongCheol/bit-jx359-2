package kr.co.webcash.service.notification;

import java.util.List;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.notification.Notificable;
import kr.co.webcash.domain.notification.Notification;

public interface NotificationService {

	void save(Notification notification);
	boolean delete(Notification notification);

	List<Notification> listByBlog(Blog blog);
	List<Notification> listByBlog(Blog blog, int pageNumber);
	List<Notification> listByBlogIdAndPageNumberAndPageSize(String blogId, int pageNumber, int pageSize);

	List<Notification> listPublicByBlogAndPage(Blog blog, int pageNumber, int pageSize);

	boolean sendNotification(Notificable notificable);
	boolean sendNotification(Notificable notificable, boolean isPublic);

	int countByBlogId(String blogId);



}
