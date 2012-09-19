package kr.co.webcash.web.blog;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.LoginUser;
import kr.co.webcash.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loginUser")
@RequestMapping("/blog")
public class BlogController {
	@Autowired private BlogService blogService;
	
	@RequestMapping("/settings")
	public void settings(){
	
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@RequestParam String title, @ModelAttribute LoginUser loginUser){
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setId(loginUser.getLoginId());
		blog.setOwner(loginUser.getLoginId());
		blog.setDateCreated(new Date(System.currentTimeMillis()));
		
		blogService.createBlog(blog);
		
		return "redirect:/";
	}
}
