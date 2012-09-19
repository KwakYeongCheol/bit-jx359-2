package kr.co.webcash.web.visitor;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loginUser")
@RequestMapping("/{blogId}/visitor")
public class VisitorController {
	
	@Autowired private BlogService blogService;
	@Autowired private VisitorService visitorService;
	@RequestMapping
	public String main(@PathVariable String blogId){
		
		return "redirect:/" + blogId;
	}
	
	@RequestMapping(value="/wirteAction", method=RequestMethod.POST)
	public String wirteAction(HttpSession session, @RequestParam String contents, @PathVariable String blogId){
		LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");
		
		if(loginUser != null){
			Blog blog = new Blog();
			blog.setId(blogId);
			
			Visitor visitor = new Visitor();
			
			int lastId = visitorService.findLastIdByBlogId(blog.getId());
			visitor.setId(String.valueOf(lastId + 1));
			visitor.setWriter(loginUser.getLoginId());
			visitor.setContents(contents);
			visitor.setBlog(blog);
			visitor.setDateCreated(new Date(System.currentTimeMillis()));
			
			visitorService.write(visitor);
		}
		
		return "redirect:/" + blogId;
	}
	
}
