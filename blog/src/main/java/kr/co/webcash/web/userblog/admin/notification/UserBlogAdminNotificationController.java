package kr.co.webcash.web.userblog.admin.notification;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.notification.Notification;
import kr.co.webcash.service.notification.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/admin/notification")
public class UserBlogAdminNotificationController {
	
	@Autowired private NotificationService notificationService;
	
	@RequestMapping
	public String home(@PathVariable String blogId,
			@RequestParam(defaultValue="1") int pageNumber, @RequestParam(defaultValue="10") int pageSize,
			Model model){
		
		Page page = new Page(pageNumber, notificationService.countByBlogId(blogId));
		
		model.addAttribute("adminNotificationList", notificationService.listByBlogIdAndPageNumberAndPageSize(blogId, pageNumber, pageSize));
		model.addAttribute("page", page);
		
		return "/userblog/admin/notification/home";
	}
	
	@RequestMapping("/delete")
	public String delete(@PathVariable String blogId, @RequestParam long id, @RequestParam(required=false) String redirectURI){
		notificationService.delete(new Notification(id, new Blog(blogId)));
		
		if(redirectURI != null){
			return "redirect:" + redirectURI;
		}
		
		return "redirect:/" + blogId + "/admin";
	}

}
