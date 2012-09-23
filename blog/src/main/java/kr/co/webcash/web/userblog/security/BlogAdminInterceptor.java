package kr.co.webcash.web.userblog.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.utils.URLUtils;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BlogAdminInterceptor extends HandlerInterceptorAdapter{
	
	@Inject private Provider<LoginUser> loginUserProvider;
	@Autowired private BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LoginUser loginUser = this.loginUserProvider.get(); 
		
		if(loginUser.isLoggedIn()){
			String blogId = URLUtils.getBlogId(request.getRequestURI(), request.getContextPath());
			if(isAdmin(loginUser.getLoginId(), blogId)){
				return true;
			}else{
				response.sendRedirect(request.getContextPath() + "/" + blogId);
				return false;
			}
		}
		
		String requestURL = URLUtils.getRequestURL(request.getRequestURI(), request.getContextPath());
		
		response.sendRedirect(request.getContextPath() + "/login?redirectURI=" + requestURL);
		return false;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		
		List<String> cssList = new ArrayList<String>();
		cssList.add("css/blog/admin/blog_admin_default.css");
		modelAndView.getModel().put("cssList", cssList);
	}

	private boolean isAdmin(String loginId, String blogId){
		Blog blog = this.blogService.findById(blogId);
		
		if(blog != null && blog.getOwner().equals(loginId)){
			return true;
		}
		
		return false;
	}
	
}