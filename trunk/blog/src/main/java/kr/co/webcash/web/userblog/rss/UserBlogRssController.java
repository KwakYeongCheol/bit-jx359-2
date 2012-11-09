package kr.co.webcash.web.userblog.rss;

import kr.co.webcash.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/{blogId}/rss")
public class UserBlogRssController {
	@Autowired private PostService postService;
	
	@RequestMapping
	public ModelAndView home(@PathVariable String blogId, UriComponentsBuilder uriComponentsBuilder, Model model){
		UriComponents components = uriComponentsBuilder.build();
		
		ModelAndView mav = new ModelAndView(new UserBlogRssFeedView());
		mav.addObject("serverURI", components.toUriString());
		mav.addObject("blogId", blogId);
		mav.addObject("postList", postService.listByBlogId(blogId));
		
		return mav;
	}
}
