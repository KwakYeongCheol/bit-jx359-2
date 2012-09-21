package kr.co.webcash.web.userblog.visitor;

import java.util.Date;

import javax.servlet.http.HttpSession;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.LoginUser;
import kr.co.webcash.domain.Visitor;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.VisitorService;

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
@SessionAttributes(value={"visitor","loginUser"})
@RequestMapping("/{blogId}/visitor")
public class VisitorController {
	
	@Autowired private BlogService blogService;
	@Autowired private VisitorService visitorService;
	@RequestMapping
	public String main(@PathVariable String blogId,Model model,HttpSession session){	
		model.addAttribute("visitorList",visitorService.listByBlogId(blogId));
		model.addAttribute("visitor",new Visitor());
		return "userblog/visitor/home";
	}
	@RequestMapping(value="/wirteAction", method=RequestMethod.POST)
	public String wirteAction(HttpSession session, @ModelAttribute Visitor visitor, @PathVariable String blogId){
		LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");
		
		if(loginUser != null){
			Blog blog = new Blog();
			blog.setId(blogId);
		
			int lastId = visitorService.findLastIdByBlogId(blog.getId());
			visitor.setId(String.valueOf(lastId + 1));
			visitor.setWriter(loginUser.getLoginId());
			visitor.setBlog(blog);
			visitor.setDateCreated(new Date(System.currentTimeMillis()));
			
			visitorService.write(visitor);
		}
		
		return "redirect:/" + blogId +"/visitor";
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute LoginUser loginUser, @PathVariable String blogId, @RequestParam String id, Model model){
		
		Visitor visitor = visitorService.findByIdAndBlogId(id, blogId);
		
		if(visitor != null){
			if(loginUser.getLoginId().equals(visitor.getWriter())){
				model.addAttribute("visitor", visitor);
				return "/userblog/visitor/modify";
			}
		}
		
		return "redirect:/" + blogId;	
	}
	@RequestMapping(value="/modifyAction", method=RequestMethod.POST)
	public String modifyAction(@ModelAttribute LoginUser loginUser, @PathVariable String blogId, @ModelAttribute Visitor modifiedVisitor, @RequestParam String id){
		Visitor visitor = visitorService.findByIdAndBlogId(id, blogId);
		
		if(visitor != null){
			if(loginUser.getLoginId().equals(visitor.getWriter())){
				Blog blog = new Blog();
				blog.setId(blogId);
				modifiedVisitor.setId(id);
				modifiedVisitor.setBlog(blog);
				
				visitorService.update(modifiedVisitor);
			}
		}
		
		return "redirect:/" + blogId +"/visitor";	
	}
	@RequestMapping("/delete")
	public String delete(@ModelAttribute LoginUser loginUser, @RequestParam String id, @PathVariable String blogId){
		
		Blog currentBlog = blogService.findById(blogId);
		
		Visitor visitor = visitorService.findByIdAndBlogId(id, blogId);
		
		if(visitor != null && currentBlog != null){
			if(loginUser.getLoginId().equals(visitor.getWriter()) || loginUser.getLoginId().equals(currentBlog.getOwner())){
				Visitor deletedVisitor = new Visitor();
				deletedVisitor.setId(id);
				Blog blog = new Blog();
				blog.setId(blogId);
				deletedVisitor.setBlog(blog);
				
				visitorService.delete(deletedVisitor);
			}
		}
		return "redirect:/" + blogId +"/visitor";
	}
}
