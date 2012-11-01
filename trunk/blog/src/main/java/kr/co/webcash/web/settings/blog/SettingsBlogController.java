package kr.co.webcash.web.settings.blog;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.web.security.LoginUser;
import kr.co.webcash.web.validator.BlogValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("blog")
@RequestMapping("/settings/blog")
public class SettingsBlogController {
	
	@Autowired private BlogService blogService;
	
	@Autowired private BlogValidator blogValidator;
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().getLoginUser();
	}
	
	@RequestMapping()
	public String blogHome(){
		return "/settings/blog/home";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createForm(Model model){
		model.addAttribute("blog", new Blog());
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute Blog blog, BindingResult result, SessionStatus status){
		this.blogValidator.validate(blog, result);
		
		if(!result.hasErrors()){
			blog.setOwner(loginUser().getLoginId());
			blog.setDateCreated(new Date(System.currentTimeMillis()));
			
			blogService.createBlog(blog);
			
			status.setComplete();
			this.loginUserProvider.get().addBlog(blog);
			
			return "redirect:/" + blog.getId();
		}
		
		return "/settings/blog/create";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyForm(@RequestParam("id") String blogId, Model model){
		if(blogService.isAdmin(blogId, loginUser())){
			model.addAttribute("blog", blogService.findById(blogId));
			return "/settings/blog/modify";
		}
		
		return "redirect:/settings/blog";
	}
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute Blog blog, Model model, SessionStatus status){
		if(blogService.isAdmin(blog.getId(), loginUser())){
			blogService.modify(blog);
			status.setComplete();
			return "redirect:/settings/blog";
		}
		
		return "redirect:/settings/blog/modify?id=" + blog.getId();
	}
}
