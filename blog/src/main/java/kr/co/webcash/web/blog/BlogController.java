package kr.co.webcash.web.blog;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Post;
import kr.co.webcash.domain.Scrap;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("post")
@RequestMapping("/blog")
public class BlogController {
	@Autowired private BlogService blogService;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().loginUser();
	}
	
	@RequestMapping("/settings")
	public void settings(Model model){
		model.addAttribute("blog", blogService.findByUserLoginId(loginUser().getLoginId()));
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@RequestParam String title){
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setId(loginUser().getLoginId());
		blog.setOwner(loginUser().getLoginId());
		blog.setDateCreated(new Date(System.currentTimeMillis()));
		
		blogService.createBlog(blog);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@RequestParam String title){
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setId(loginUser().getLoginId());
		
		blogService.modify(blog);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/scrap", method=RequestMethod.POST)
	public String scrap(@ModelAttribute Scrap scrap,Model model){
		Blog loginUserBlog = blogService.findByUserLoginId(loginUser().getLoginId());
		
		if(loginUserBlog.getId().equals(scrap.getScrappedBlog().getId())){
			return "redirect:/" + scrap.getScrappedBlog().getId();
		}else{
			Post post = new Post();
			post.setScrap(scrap);
			model.addAttribute("post", post);
			return "redirect:/" + loginUserBlog.getId() + "/admin/post/scrap";
		}
	}
}
