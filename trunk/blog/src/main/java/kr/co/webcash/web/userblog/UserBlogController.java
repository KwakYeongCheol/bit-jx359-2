package kr.co.webcash.web.userblog;


import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Category;
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
@RequestMapping("/{blogId}")
public class UserBlogController {
	@Autowired private PostService postService;
	@Autowired private PostRevisionService postRevisionService;
	@Autowired private BlogService blogService;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	private LoginUser loginUser(){
		return loginUserProvider.get();
	}
	
	@RequestMapping
	public String main(@PathVariable String blogId, @RequestParam(defaultValue="1") int pageNumber , Model model){
		if(!blogService.isExist(blogId))	return "redirect:/";
		
		LoginUser loginUser = loginUserProvider.get();
		
		Category category = new Category("모든 글");
		
		if(loginUser.isLoggedIn() && blogService.isAdmin(blogId, loginUser.getLoginUser())){
			Page page = postService.getPage(blogId, pageNumber);
			category.setPostList(postService.listByBlogIdAndPage(blogId, page));
			model.addAttribute("pageCategory", category);
			model.addAttribute("page", page);
		}else{
			Page page = postService.getPagePublic(blogId, pageNumber);
			category.setPostList(postService.listPublicByBlogIdAndPage(blogId, page));
			model.addAttribute("pageCategory", category);
			model.addAttribute("page", page);
		}
		 
//		return "/userblog/home";
		return "/jinbo/home";
	}
	
	@RequestMapping("/{postId}")
	public String postView(@PathVariable String blogId, @PathVariable long postId, Model model){
		
		Post findPost = postService.findByBlogIdAndDisplayId(blogId, postId);
		
		if(findPost == null){
			model.addAttribute("message", "해당 글이 존재하지 않습니다.");
			return "/jinbo/common/notfound";
		}
		
		if(findPost.getIsPublic() || loginUser().isMyBlog(blogId)){
			model.addAttribute("post", findPost);
			return "/jinbo/post/view";
		}else{
			model.addAttribute("message", "해당 글은 존재하지 않거나 비공개된 글입니다.");
			return "/jinbo/common/notfound";
		}
	}
	
	@RequestMapping("/{postId}/revision/{revisionId}")
	public String getRevisionContents(@PathVariable String blogId, @PathVariable long postId, @PathVariable long revisionId, Model model){
		Post post = postService.findByBlogIdAndDisplayId(blogId, postId);
		
		post.setContents(post.getContents(revisionId));
		model.addAttribute("post", post);

		return "/jinbo/post/view";
	}
	
	@RequestMapping("/{postId}/compare/{revisionId}")
	public String compare(@PathVariable String blogId, @PathVariable long postId, @PathVariable long revisionId, Model model){
		Post post = postService.findByBlogIdAndDisplayId(blogId, postId);
		post.setContents(post.getCompareContents(revisionId));
		
		model.addAttribute("post", post);
		
		return "/jinbo/post/view";
	}
	
	@RequestMapping("/{postId}/compare/{fromRevisionId}/{toRevisionId}")
	public String compare(@PathVariable String blogId, @PathVariable long postId, 
			@PathVariable long fromRevisionId, @PathVariable long toRevisionId, Model model){
		Post post = postService.findByBlogIdAndDisplayId(blogId, postId);
		
		post.setContents(post.getCompareContents(fromRevisionId, toRevisionId));
		model.addAttribute("post", post);
		
		
		return "/jinbo/post/view";
	}
}
