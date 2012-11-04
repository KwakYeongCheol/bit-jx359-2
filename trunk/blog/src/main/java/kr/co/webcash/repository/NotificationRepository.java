package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Notification;

public interface NotificationRepository {
	public void insert(Notification notification);

	public Notification findLast();
	
	public List<Notification> findAllByBlogId(String blogId);
}
