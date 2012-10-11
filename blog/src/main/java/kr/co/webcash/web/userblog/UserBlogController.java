package kr.co.webcash.web.userblog;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.post.Post;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.PostService;
import kr.co.webcash.web.security.LoginUser;

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
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@RequestMapping
	public String main(@PathVariable String blogId, Model model){
		if(!blogService.isExist(blogId))	return "redirect:/";
		
		LoginUser loginUser = loginUserProvider.get();
		if(loginUser.isLoggedIn() && blogService.isAdmin(blogId, loginUser.getLoginUser())){
			model.addAttribute("postList", postService.listAll(blogId));
		}else{
			model.addAttribute("postList", postService.listPublic(blogId));
		}
		
		return "/userblog/home";
	}
	
	@RequestMapping("/{postId}")
	public String postView(@PathVariable String blogId, @PathVariable long postId, Model model){
		Post currentPost = postService.findByBlogIdAndDisplayId(blogId, postId);
		
		model.addAttribute("post", currentPost);
		
		return "/userblog/post/view";
	}
}
