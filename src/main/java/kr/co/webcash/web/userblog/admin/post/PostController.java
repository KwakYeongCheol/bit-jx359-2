package kr.co.webcash.web.userblog.admin.post;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/admin/post")
public class PostController {
	
	@Autowired private PostService postService;
	@Autowired private BlogService blogService;
	
	@RequestMapping("/write")
	public String write(){
		return "/userblog/admin/post/write";
	}
	
	@RequestMapping(value= "/writeAction", method=RequestMethod.POST)
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
	
	@RequestMapping("/modify")
	public String modify(@PathVariable String blogId, @RequestParam String id, Model model){
		
		Post post = postService.findByIdAndBlogId(id,blogId);
		
		model.addAttribute("post", post);
		
		return "/userblog/admin/post/modify";
	}
	
	@RequestMapping(value = "/modifyAction", method = RequestMethod.POST)
	public String modifyAction(@PathVariable String blogId, @RequestParam String id, @RequestParam String title, @RequestParam String contents){
		Post post = new Post();
		Blog blog = new Blog();
		blog.setId(blogId);
		post.setId(id);
		post.setBlog(blog);
		post.setTitle(title);
		post.setContents(contents);
		
		postService.update(post);
		
		return "redirect:/" + blogId + "/admin";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam String id, @PathVariable String blogId){
		Post post = new Post();
		post.setId(id);
		Blog blog = new Blog();
		blog.setId(blogId);
		post.setBlog(blog);
		
		postService.delete(post);
		
		return "redirect:/" + blogId + "/admin";
	}
}
