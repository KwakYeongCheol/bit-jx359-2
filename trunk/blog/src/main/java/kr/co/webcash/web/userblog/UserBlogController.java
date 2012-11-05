package kr.co.webcash.web.userblog;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.service.PostRevisionService;
import kr.co.webcash.service.PostService;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}")
public class UserBlogController {
	
	@Autowired private PostService postService;
	@Autowired private PostRevisionService postRevisionService;
	@Autowired private BlogService blogService;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@RequestMapping
	public String main(@PathVariable String blogId, @RequestParam(required=false, defaultValue="1") int pageNum , Model model){
		List<Post> postList;
		
		if(!blogService.isExist(blogId))	return "redirect:/";
		
		LoginUser loginUser = loginUserProvider.get();
		/*if(loginUser.isLoggedIn() && blogService.isAdmin(blogId, loginUser.getLoginUser())){
			model.addAttribute("postList", postService.listAll(blogId));
		}else{
			model.addAttribute("postList", postService.listPublic(blogId));
		}*/
		
		if(loginUser.isLoggedIn() && blogService.isAdmin(blogId, loginUser.getLoginUser())){
			Page page = postService.getPage(blogId, pageNum);
			postList = postService.listAllByPage(page, blogId);
			model.addAttribute("postList", postList);
			model.addAttribute("page", page);
		}else{
			Page page = postService.getPagePublic(blogId, pageNum);
			postList = postService.listPublicByPage(page, blogId);
			model.addAttribute("postList", postList);
			model.addAttribute("page", page);
		}
		 
		return "/userblog/home";
	}
	
	@RequestMapping("/{postId}")
	public String postView(@PathVariable String blogId, @PathVariable long postId, Model model){
		Post currentPost = postService.findByBlogIdAndDisplayId(blogId, postId);
		
		model.addAttribute("post", currentPost);
		
		return "/userblog/post/view";
	}
	
	@RequestMapping("/{postId}/revision/{revisionId}")
	public String getRevisionContents(@PathVariable String blogId, @PathVariable long postId, @PathVariable long revisionId, Model model){
		Post post = postService.findByBlogIdAndDisplayId(blogId, postId);
		
		String contents = postRevisionService.getContents(post, revisionId);
		
		model.addAttribute("contents", contents);

		return "/userblog/post/revision";
	}
	
	@RequestMapping("/{postId}/compare/{revisionId}")
	public String compare(@PathVariable String blogId, @PathVariable long postId, @PathVariable long revisionId, Model model){
		Post post = postService.findByBlogIdAndDisplayId(blogId, postId);
		
		model.addAttribute("contents", postRevisionService.getCompareHtml(post, revisionId));
		
		return "/userblog/post/compare";
	}
}
