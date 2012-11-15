package kr.co.webcash.web.userblog.admin;

import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.service.post.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/admin")
public class UserBlogAdminController {
	
	@Autowired private BlogService blogService;
	@Autowired private PostService postService;
	
	@RequestMapping
	public String main(@PathVariable String blogId, @RequestParam(defaultValue="1") int pageNumber, Model model){
		model.addAttribute("postList", postService.listByBlogIdAndPageNumberAndPageSize(blogId, pageNumber, 5));
		
		return "/userblog/admin/home";
	}
	
}
