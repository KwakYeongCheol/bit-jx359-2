package kr.co.webcash.domain.notification;

import kr.co.webcash.domain.Blog;

public interface Notificable {

	public Blog getNotificationBlog();
	
	public String getNotificationURI();
	
	public String getNotificationContents();

}
