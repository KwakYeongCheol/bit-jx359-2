package kr.co.webcash.web.userblog.comment;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.CommentType;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.CommentService;
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
	@Autowired private CommentService commentService;
	
	
	@Inject private Provider<LoginUser> loginUserProvider;
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().getLoginUser();
	}
	
	
	@RequestMapping(value= "writeAction", method=RequestMethod.POST)
	public String writeAction(@PathVariable String blogId, @RequestParam long targetId, @RequestParam String type, @RequestParam String contents){
		
		
			Blog blog = new Blog();
			blog.setId(blogId);
			Comment comment = new Comment();
			comment.setId(commentService.findLastIdByBlogIdAndTargetIdAndType(blogId, targetId, type) + 1);
			comment.setBlog(blog);
			comment.setTargetId(targetId);
			comment.setType(CommentType.valueOf(type));
			comment.setContents(contents);
			comment.setWriter(loginUser());
			comment.setDateCreated(new Date(System.currentTimeMillis()));
			
			commentService.save(comment);
			
		if(type.toString().equals("guestbook")){
			return "redirect:/" + blogId + "/guestbook";
		}
		
		return "redirect:/" + blogId;
	}
	
	@RequestMapping("/modify")
	public String modify(@PathVariable String blogId, @RequestParam long id, @RequestParam long targetId, @RequestParam String type, Model model){
		Comment comment = commentService.findByIdAndBlogIdAndTargetIdAndType(id,blogId,targetId, type);
		
		if(comment.getWriter().getLoginId().equals(loginUser().getLoginId())){
			model.addAttribute("comment", comment);
			return "/userblog/comment/modify";
		}
		
		return "redirect:/" + blogId;
	}
	
	@RequestMapping(value="/modifyAction", method=RequestMethod.POST)
	public String modifyAction(@PathVariable String blogId, @RequestParam long id, @RequestParam long targetId, @RequestParam String type, @RequestParam String contents){
		Comment comment = commentService.findByIdAndBlogIdAndTargetIdAndType(id, blogId, targetId, type);
		
		if(comment.getWriter().getLoginId().equals(loginUser().getLoginId())){
			Blog updatedBlog = new Blog();
			updatedBlog.setId(blogId);		
			Comment updatedComment = new Comment();
			updatedComment.setId(id);
			updatedComment.setBlog(updatedBlog);
			updatedComment.setTargetId(targetId);
			updatedComment.setType(CommentType.valueOf(type));
			updatedComment.setContents(contents);
			
			commentService.update(updatedComment);
		}
		
		return "redirect:/" + blogId; 
	}
	
	@RequestMapping("/delete")
	public String delete(@PathVariable  String blogId, @RequestParam long id, @RequestParam long targetId, @RequestParam String type){
		Blog currentBlog = blogService.findById(blogId);
		Comment comment = commentService.findByIdAndBlogIdAndTargetIdAndType(id, blogId, targetId, type);
		
		if(currentBlog.getOwner().equals(loginUser().getLoginId()) || comment.getWriter().getLoginId().equals(loginUser().getLoginId())){
			Blog deletedBlog = new Blog();
			deletedBlog.setId(blogId);		
			Comment deletedComment = new Comment();
			deletedComment.setId(id);
			deletedComment.setBlog(deletedBlog);
			deletedComment.setTargetId(targetId);
			deletedComment.setType(CommentType.valueOf(type));
			
			commentService.delete(deletedComment);
		}
		
		return "redirect:/" + blogId;
	}
}
