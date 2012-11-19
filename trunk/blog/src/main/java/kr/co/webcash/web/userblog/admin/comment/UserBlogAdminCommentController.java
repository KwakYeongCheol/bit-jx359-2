package kr.co.webcash.web.userblog.admin.comment;

import kr.co.webcash.domain.Page;
import kr.co.webcash.service.comment.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/admin/comment")
public class UserBlogAdminCommentController {
	
	@Autowired private CommentService commentService;
	
	@RequestMapping
	public String home(@PathVariable String blogId,
			@RequestParam(defaultValue="1") int pageNumber, @RequestParam(defaultValue="10") int pageSize,
			Model model){
		
		Page page = new Page(pageNumber, commentService.countByBlogId(blogId));
		model.addAttribute("commentList", commentService.listByBlogIdAndPage(blogId, page));
		model.addAttribute("page");
		
		return "/userblog/admin/comment/home";
	}

}
