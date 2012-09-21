package kr.co.webcash.web.userblog.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AddBlogInfoInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired private BlogService blogService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		
		String blogId = findBlogIdFromRequestURI(request.getRequestURI(), 
												request.getContextPath());
		
		Blog blog = blogService.findById(blogId);
		if(blog != null){
			modelAndView.getModel().put("blog", blog);
			modelAndView.getModel().put("htmlTitle", blog.getTitle());
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
