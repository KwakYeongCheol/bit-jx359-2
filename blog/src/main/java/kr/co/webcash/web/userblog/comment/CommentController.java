package kr.co.webcash.web.userblog.comment;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.CommentTarget;
import kr.co.webcash.domain.CommentType;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.CommentService;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.web.security.LoginUser;
import kr.co.webcash.web.validator.CommentValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = { "post" })
@RequestMapping("/{blogId}/comment")
public class CommentController {

	@Autowired private BlogService blogService;
	@Autowired private CommentService commentService;
	@Autowired private CommentValidator commentValidator;

	@Inject private Provider<LoginUser> loginUserProvider;

	@ModelAttribute("loginUser")
	public User loginUser() {
		return this.loginUserProvider.get().getLoginUser();
	}

	@RequestMapping(value = "writeAction", method = RequestMethod.POST)
	public String writeAction(@PathVariable String blogId,
			@RequestParam long targetDisplayId, 
			@RequestParam String type,
			@RequestParam String contents) {
		
		long targetId = commentService.getTargetIdByBlogIdAndCommentTypeAndDisplayId(blogId, CommentType.valueOf(type), targetDisplayId);
		long lastDisplayId = commentService.findLastDisplayIdByBlogIdAndCommentType(blogId, CommentType.valueOf(type));
		
		Comment comment = new Comment();
		comment.setTarget(new CommentTarget(targetId, CommentType.valueOf(type)));
		comment.setDisplayId(lastDisplayId + 1);
		comment.setWriter(loginUser());
		comment.setContents(contents);
		comment.setDateCreated(new Date(System.currentTimeMillis()));

		commentService.save(comment);

		if (type.toString().equals("guestbook")) {
			return "redirect:/" + blogId + "/guestbook";
		}

		return "redirect:/" + blogId;
	}

	@RequestMapping("/modify")
	public String modify(@PathVariable String blogId, @RequestParam long displayId,
			@RequestParam long targetId, @RequestParam String type, Model model) {
		Comment comment = commentService.findByTargetIdAndCommentTypeAndDisplayId(targetId, CommentType.valueOf(type), displayId);
		
		if (comment.getWriter().getLoginId().equals(loginUser().getLoginId())) {
			model.addAttribute("comment", comment);
			return "/userblog/comment/modify";
		}

		return "redirect:/" + blogId;
	}

	@RequestMapping(value = "/modifyAction", method = RequestMethod.POST)
	public String modifyAction(@PathVariable String blogId, 
			@ModelAttribute Comment comment, BindingResult result) {
		
		this.commentValidator.validate(comment, result);
		
		if (!result.hasErrors()) {
//			comment = commentService.findByIdAndBlogIdAndTargetIdAndType(id,
//					blogId, targetId, type);
			
			Comment findComment = commentService.findByTargetIdAndCommentTypeAndDisplayId(
					comment.getTarget().getId(), 
					comment.getTarget().getType(), 
					comment.getDisplayId());
			
			if (findComment.getWriter().getLoginId().equals(loginUser().getLoginId())) {
				comment.setId(findComment.getId());
				commentService.update(comment);
			}
			return "redirect:/" + blogId;
		}
		return "/userblog/comment/modify";
	}

	@RequestMapping("/delete")
	public String delete(@PathVariable String blogId, @RequestParam long displayId,
			@RequestParam long targetId, @RequestParam String type) {
		Blog currentBlog = blogService.findById(blogId);
		Comment comment = commentService.findByTargetIdAndCommentTypeAndDisplayId(targetId, CommentType.valueOf(type), displayId);

		if (currentBlog.getOwner().equals(loginUser().getLoginId())
				|| comment.getWriter().getLoginId().equals(loginUser().getLoginId())) {
			commentService.delete(comment);
		}

		return "redirect:/" + blogId;
	}
}
