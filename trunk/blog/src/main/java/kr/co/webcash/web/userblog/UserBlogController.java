package kr.co.webcash.web.userblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{blogId}")
public class UserBlogController {
	
	@RequestMapping
	public String main(@PathVariable String blogId, Model model){

		model.addAttribute("htmlTitle", blogId);
		model.addAttribute("blogId", blogId);
		
		return "/userblog/home";
	}

}
