package kr.co.webcash.web.userblog.admin;

import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}
