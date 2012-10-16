package kr.co.webcash.web.blog;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.web.security.LoginUser;
import kr.co.webcash.web.validator.BlogValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("post")
@RequestMapping("/blog")
public class BlogController {
	@Autowired private BlogService blogService;
	@Autowired private BlogValidator blogValidator;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().getLoginUser();
	}
	
	@RequestMapping("/settings")
	public void settings(){
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@ModelAttribute("blog") Blog blog, BindingResult result){
		this.blogValidator.validate(blog, result);
		if(!result.hasErrors()){
			blog.setId(loginUser().getLoginId());
			blog.setOwner(loginUser().getLoginId());
			blog.setDateCreated(new Date(System.currentTimeMillis()));
			
			blogService.createBlog(blog);
			this.loginUserProvider.get().setBlog(blog);
			return "redirect:/";
		}
		return "/blog/settings";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute("blog") Blog blog, BindingResult result){
		this.blogValidator.validate(blog, result);
		if(!result.hasErrors()){
			blog.setId(loginUser().getLoginId());
			
			blogService.modify(blog);
			this.loginUserProvider.get().setBlog(blog);
			return "redirect:/";
		}
		return "/blog/settings";
	}
}
