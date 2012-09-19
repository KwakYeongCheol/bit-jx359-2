package kr.co.webcash.web.userblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.webcash.domain.LoginUser;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserSecurityInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String requestURI = request.getRequestURI();
		
		int startWithAdmin = requestURI.indexOf("/admin");
		int startIndex = 0;
		
		while(true){
			int tmp = requestURI.indexOf("/", startIndex + 1);
			
			if(tmp >= startWithAdmin)	break;
			
			startIndex = tmp;
		}
		
		String blogId = requestURI.substring(startIndex + 1, startWithAdmin);
		
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute("loginUser");
		
		if(loginUser != null){
			if(loginUser.getLoginId().equals(blogId))	return true;
		}
		
		response.sendRedirect(request.getContextPath() + "/" + blogId);
		return false;
	}
	
	

}
