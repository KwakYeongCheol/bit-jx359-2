package kr.co.webcash.web.userblog.guestbook;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Guestbook;
import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.user.User;
import kr.co.webcash.service.GuestbookService;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.service.notification.NotificationService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("guestbook")
@RequestMapping("/{blogId}/guestbook")
public class GuestbookController {
	
	@Autowired private BlogService blogService;
	@Autowired private GuestbookService guestbookService;
	@Autowired private NotificationService notificationService;
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@RequestMapping
	public String main(@PathVariable String blogId, Model model){	
		model.addAttribute("guestbookList",guestbookService.listByBlogId(blogId));
		model.addAttribute("guestbook",new Guestbook());
		return "/jinbo/guestbook/home";
	}
	
	@RequestMapping("/{guestbookDisplayId}")
	public String guestbookView(@PathVariable String blogId, @PathVariable long guestbookDisplayId, Model model){
		model.addAttribute("guestbook",new Guestbook());
		model.addAttribute("findGuestbook", guestbookService.findByBlogIdAndDisplayId(blogId, guestbookDisplayId));
		return "/jinbo/guestbook/view";
	}
	
	@RequestMapping(value="/wirteAction", method=RequestMethod.POST)
	public String wirteAction(@ModelAttribute Guestbook guestbook, @PathVariable String blogId, SessionStatus sessionStatus){
		if(loginUser() != null){
			guestbook.setBlog(new Blog(blogId));
			guestbook.setWriter(loginUser());
			
			guestbook.setContents(guestbook.getContents().replace("\n", "<br />"));
			
			guestbookService.save(guestbook);
			notificationService.sendNotification(guestbook);
			sessionStatus.setComplete();
		}
		
		return "redirect:/" + blogId +"/guestbook";
	}
	
	@RequestMapping("/modify")
	public String modify(@PathVariable String blogId, @RequestParam long displayId, Model model){
		Guestbook guestbook = guestbookService.findByBlogIdAndDisplayId(blogId, displayId);
		
		if(guestbook != null){
			if(loginUser().getLoginId().equals(guestbook.getWriter().getLoginId())){
				model.addAttribute("guestbook", guestbook);
				return "/userblog/guestbook/modify";
			}
		}
		
		return "redirect:/" + blogId;	
	}
	
	@RequestMapping(value="/modifyAction", method=RequestMethod.POST)
	public String modifyAction(@PathVariable String blogId, @ModelAttribute Guestbook modifiedGuestbook, @RequestParam long displayId){
		Guestbook guestbook = guestbookService.findByBlogIdAndDisplayId(blogId, displayId);
		
		if(guestbook != null){
			if(loginUser().getLoginId().equals(guestbook.getWriter().getLoginId())){
				modifiedGuestbook.setId(guestbook.getId());
				guestbookService.update(modifiedGuestbook);
			}
		}
		
		return "redirect:/" + blogId +"/guestbook";	
	}
	
	@RequestMapping("/delete")
	public String delete(@PathVariable String blogId, @RequestParam long displayId, @RequestParam(required=false) String redirectURI){
		Blog currentBlog = blogService.findById(blogId);
		Guestbook guestbook = guestbookService.findByBlogIdAndDisplayId(blogId, displayId);
		
		if(guestbook != null && currentBlog != null){
			if(loginUser().getLoginId().equals(guestbook.getWriter().getLoginId()) || loginUser().getLoginId().equals(currentBlog.getOwner())){
				guestbookService.delete(guestbook);
			}
		}
		
		if(redirectURI != null){
			return "redirect:" + redirectURI;
		}
		
		return "redirect:/" + blogId +"/guestbook";
	}
	
	
	private User loginUser() {
		return this.loginUserProvider.get().getLoginUser();
	}
}
