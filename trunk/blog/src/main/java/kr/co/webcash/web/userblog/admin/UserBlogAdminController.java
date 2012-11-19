package kr.co.webcash.web.userblog.admin;

import kr.co.webcash.service.GuestbookService;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.service.comment.CommentService;
import kr.co.webcash.service.post.PostService;

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
	@Autowired private GuestbookService guestbookService;
	@Autowired private CommentService commentService;
	
	@RequestMapping
	public String main(@PathVariable String blogId, Model model){
		model.addAttribute("postList", postService.listByBlogIdAndPageNumberAndPageSize(blogId, 1, 5));
		model.addAttribute("guestbookList", guestbookService.listByBlogIdAndPageNumberAndPageSize(blogId, 1, 5));
		model.addAttribute("commentList", commentService.listByBlogIdAndPageNumberAndPageSize(blogId, 1, 5));
		
		return "/userblog/admin/home";
	}
	
}
