package kr.co.webcash.web.userblog.guestbook;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Guestbook;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.GuestbookService;
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

@Controller
@SessionAttributes("guestbook")
@RequestMapping("/{blogId}/guestbook")
public class GuestbookController {
	
	@Autowired private BlogService blogService;
	@Autowired private GuestbookService guestbookService;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@RequestMapping
	public String main(@PathVariable String blogId,Model model){	
		model.addAttribute("guestbookList",guestbookService.listByBlogId(blogId));
		model.addAttribute("guestbook",new Guestbook());
		return "userblog/guestbook/home";
	}
	
	@RequestMapping(value="/wirteAction", method=RequestMethod.POST)
	public String wirteAction(@ModelAttribute Guestbook guestbook, @PathVariable String blogId){
		if(loginUser() != null){
			Blog blog = new Blog();
			blog.setId(blogId);
		
			guestbook.setDisplayId(guestbookService.findLastIdByBlogId(blog.getId()) + 1);
			guestbook.setWriter(loginUser().getLoginId());
			guestbook.setBlog(blog);
			guestbook.setDateCreated(new Date(System.currentTimeMillis()));
			
			guestbookService.write(guestbook);
		}
		
		return "redirect:/" + blogId +"/guestbook";
	}
	
	@RequestMapping("/modify")
	public String modify(@PathVariable String blogId, @RequestParam long displayId, Model model){
		Guestbook guestbook = guestbookService.findByBlogIdAndDisplayId(blogId, displayId);
		
		if(guestbook != null){
			if(loginUser().getLoginId().equals(guestbook.getWriter())){
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
			if(loginUser().getLoginId().equals(guestbook.getWriter())){
				modifiedGuestbook.setId(guestbook.getId());
				
				System.out.println(modifiedGuestbook);
				
				guestbookService.update(modifiedGuestbook);
			}
		}
		
		return "redirect:/" + blogId +"/guestbook";	
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam long displayId, @PathVariable String blogId){
		Blog currentBlog = blogService.findById(blogId);
		Guestbook guestbook = guestbookService.findByBlogIdAndDisplayId(blogId, displayId);
		
		if(guestbook != null && currentBlog != null){
			if(loginUser().getLoginId().equals(guestbook.getWriter()) || loginUser().getLoginId().equals(currentBlog.getOwner())){
				guestbookService.delete(guestbook);
			}
		}
		return "redirect:/" + blogId +"/guestbook";
	}
	
	
	private User loginUser() {
		return this.loginUserProvider.get().getLoginUser();
	}
}
