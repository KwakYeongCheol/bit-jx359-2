package kr.co.webcash.web.userblog.comment;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.CommentType;
import kr.co.webcash.domain.Post;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.CommentService;
import kr.co.webcash.service.PostService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value={"post"})
@RequestMapping("/{blogId}/comment")
public class CommentController {
	
	@Autowired private BlogService blogService;
	@Autowired private PostService postService;
	@Autowired private CommentService commentService;
	
	
	@Inject private Provider<LoginUser> loginUserProvider;
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().loginUser();
	}
	
	
	@RequestMapping(value= "writeAction", method=RequestMethod.POST)
	public String writeAction(@PathVariable String blogId, @RequestParam String targetId, @RequestParam String type, @RequestParam String contents){
		
		
		Blog blog = new Blog();
		blog.setId(blogId);
		Comment comment = new Comment();
		comment.setId(String.valueOf(commentService.findLastIdByBlogIdAndTargetIdAndType(blogId, targetId, type) + 1));
		comment.setBlog(blog);
		comment.setTargetId(targetId);
		comment.setType(CommentType.valueOf(type));
		comment.setContents(contents);
		comment.setWriter(loginUser());
		comment.setDateCreated(new Date(System.currentTimeMillis()));
		
		commentService.save(comment);
		
		return "redirect:/" + blogId;
	}
}
