package kr.co.webcash.web.userblog.rss;

import kr.co.webcash.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/{blogId}/rss")
public class UserBlogRssController {
	@Autowired private PostService postService;
	
	@RequestMapping
	public ModelAndView home(@PathVariable String blogId, Model model){
		ModelAndView mav = new ModelAndView(new UserBlogRssFeedView());
		mav.addObject("blogId", blogId);
		mav.addObject("postList", postService.listAll(blogId));
		
		return mav;
	}
}
