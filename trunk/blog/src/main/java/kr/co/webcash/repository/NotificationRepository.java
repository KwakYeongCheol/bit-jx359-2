package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.notification.Notification;

public interface NotificationRepository {
	public void insert(Notification notification);

	public Notification findLast();
	
	public List<Notification> findAllByBlogId(String blogId);

	public List<Notification> findAllByBlogIdAndPage(String blogId, int pageNumber,	int pageSize);

	public List<Notification> findAllPublicByBlogIdAndPage(String blogId, int pageNumber, int pageSize);

	public int countByBlogId(String blogId);

	public boolean delete(Notification notification);
}
