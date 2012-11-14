package kr.co.webcash.web.userblog.post;


import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.service.post.PostRevisionService;
import kr.co.webcash.service.post.PostService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/userblog/post")
public class UserBlogPostController {
	@Autowired private PostService postService;
	@Autowired private PostRevisionService postRevisionService;
	@Autowired private BlogService blogService;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@RequestMapping()
	public String home(@PathVariable String blogId, @RequestParam long postDisplayId, Model model) {
		model.addAttribute("post", postService.findByBlogIdAndDisplayId(blogId, postDisplayId));
		return "/userblog/post/postListBody";
	}

	
}
