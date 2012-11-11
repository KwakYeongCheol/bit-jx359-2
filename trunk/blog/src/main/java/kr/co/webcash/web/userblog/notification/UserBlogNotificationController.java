package kr.co.webcash.web.userblog.notification;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.notification.Notification;
import kr.co.webcash.service.notification.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/{blogId}/notification")
public class UserBlogNotificationController {
	
	@Autowired private NotificationService notificationService;
	
	@RequestMapping
	@ResponseBody
	public List<Map> notificationApi(
			@PathVariable String blogId,
			@RequestParam(required=false, defaultValue="1") int pageNumber){
		
		List<Notification> notificationList = notificationService.listByBlog(new Blog(blogId), pageNumber);
		List<Map> result = new LinkedList();
		
		for(Notification notification : notificationList){
			Map notificationMap = new HashMap();
			notificationMap.put("contents", notification.getContents());
			notificationMap.put("uri", notification.getUri());
			notificationMap.put("dateCreated", notification.getDateCreated());
			
			result.add(notificationMap);
		}
		
		return result;
	}
}
