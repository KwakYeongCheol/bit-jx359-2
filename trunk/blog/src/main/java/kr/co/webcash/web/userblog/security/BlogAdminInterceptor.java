package kr.co.webcash.web.userblog.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.webcash.domain.User;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BlogAdminInterceptor extends HandlerInterceptorAdapter{
	
	@Inject private Provider<LoginUser> loginUserProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String blogId = getBlogId(request.getRequestURI());
		
		User loginUser = this.loginUserProvider.get().loginUser();
		
		if(loginUser != null){
			if(loginUser.getLoginId().equals(blogId))	return true;
		}
		
		response.sendRedirect(request.getContextPath() + "/" + blogId);
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

	private String getBlogId(String requestURI) {
		int startWithAdmin = requestURI.indexOf("/admin");
		int startIndex = 0;
		
		while(true){
			int tmp = requestURI.indexOf("/", startIndex + 1);
			
			if(tmp >= startWithAdmin)	break;
			
			startIndex = tmp;
		}
		
		String blogId = requestURI.substring(startIndex + 1, startWithAdmin);
		return blogId;
	}
}
