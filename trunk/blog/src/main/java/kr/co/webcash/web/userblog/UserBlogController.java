package kr.co.webcash.web.userblog;

import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{blogId}")
public class UserBlogController {
	
	@Autowired private PostService postService;
	@Autowired private BlogService blogService;
	
	@RequestMapping
	public String main(@PathVariable String blogId, Model model){
		if(!blogService.isExist(blogId))	return "redirect:/";

		model.addAttribute("postList", postService.listByBlogId(blogId));
		
		return "/userblog/home";
	}

}
