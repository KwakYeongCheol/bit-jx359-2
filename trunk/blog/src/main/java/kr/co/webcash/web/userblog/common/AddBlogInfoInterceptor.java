package kr.co.webcash.web.userblog.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.BlogVisitHistory;
import kr.co.webcash.service.CategoryService;
import kr.co.webcash.service.NotificationService;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.service.blog.BlogVisitHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AddBlogInfoInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired private BlogService blogService;
	@Autowired private NotificationService notificationService;
	@Autowired private BlogVisitHistoryService blogVisitHistoryService;
	@Autowired private CategoryService categoryService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		
		String blogId = findBlogIdFromRequestURI(request.getRequestURI(), request.getContextPath());
		
		Blog blog = blogService.findById(blogId);
		if(blog != null){
			blogService.addVisitCount(
				new BlogVisitHistory(blog, request.getRemoteAddr(), new Date())
			);
			
			modelAndView.addObject("notificationList", notificationService.listByBlog(blog));
			modelAndView.addObject("blog", blog);
			modelAndView.addObject("categoryList", categoryService.listByBlogId(blog.getId()));
			modelAndView.addObject("htmlTitle", blog.getTitle());
			
			if(modelAndView.getModel().get("cssList") == null){
				List<String> cssList = new ArrayList<String>();
				cssList.add("css/blog/blog_default.css");
				
				modelAndView.addObject("cssList", cssList);
			}
		}
	}

	private String findBlogIdFromRequestURI(String requestURI, String contextPath) {
		int startIndex = 0;
		if(contextPath.length() == 1){
			startIndex = contextPath.length();
		}else{
			startIndex = contextPath.length() +  1;
		}
		
		int lastIndex = requestURI.indexOf("/", startIndex);
		
		if(lastIndex == -1){
			return requestURI.substring(startIndex);
		}else{
			return requestURI.substring(startIndex, lastIndex);
		}
	}

}
