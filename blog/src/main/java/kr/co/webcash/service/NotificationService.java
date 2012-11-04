package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Notification;

public interface NotificationService {

	void sendNotification(Notification notification);

	List<Notification> listByBlog(Blog blog);

}
