package kr.co.webcash.web.userblog.admin;

import java.util.Date;

import javax.servlet.http.HttpSession;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.LoginUser;
import kr.co.webcash.domain.Post;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/admin")
public class UserBlogAdminController {
	
	@Autowired private BlogService blogService;
	@Autowired private PostService postService;
	
	@RequestMapping
	public String main(@PathVariable String blogId, Model model){
		model.addAttribute("postList", postService.listByBlogId(blogId));
		
		return "/userblog/admin/home";
	}
	
	@RequestMapping("/post/write")
	public String write(){
		return "/userblog/admin/post/write";
	}
	
	@RequestMapping(value= "/post/writeAction", method=RequestMethod.POST)
	public String writeAction(@RequestParam String title, @RequestParam String contents, HttpSession session){
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		String redirectUrl = "redirect:/";
		if(loginUser != null){
			Blog blog = blogService.findByUserLoginId(loginUser.getLoginId());
			
			Post post = new Post();
			post.setId(String.valueOf(postService.findLastIdByBlogId(blog.getId()) + 1));
			post.setBlog(blog);
			post.setTitle(title);
			post.setContents(contents);
			post.setDateCreated(new Date(System.currentTimeMillis()));
			
			postService.save(post);
			redirectUrl = "redirect:/" + blog.getId() + "/admin";
		}
		
		return redirectUrl;
	}
	
}
