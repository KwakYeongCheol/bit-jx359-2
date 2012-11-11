package kr.co.webcash.web.blog;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.user.User;
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
@RequestMapping("/blog")
@SessionAttributes("blog")
public class BlogController {
	@Autowired private BlogService blogService;
	@Autowired private BlogValidator blogValidator;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().getLoginUser();
	}
	
	@RequestMapping
	public String home(Model model){
		model.addAttribute("blogList", blogService.findAllByUserLoginId(loginUser().getLoginId()));
		return "/blog/home";
	}
	
	@RequestMapping("/settings")
	public String settings(@RequestParam("id") String blogId, Model model){
		Blog blog = blogService.findById(blogId);
		
		if(blog != null && blog.getOwner().equals(loginUser().getLoginId())){
			model.addAttribute("blog", blog);
			return "/blog/settings";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute("blog") Blog blog, BindingResult result, SessionStatus status){
		this.blogValidator.validate(blog, result);
		
		if(!result.hasErrors()){
			blogService.modify(blog);
			this.loginUserProvider.get().addBlog(blog);
			status.setComplete();
			return "redirect:/";
		}
		return "/blog/settings";
	}
}
