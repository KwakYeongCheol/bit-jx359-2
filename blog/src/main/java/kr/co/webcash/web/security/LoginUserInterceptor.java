package kr.co.webcash.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.webcash.domain.LoginUser;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginUserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute("loginUser");
		
		if(loginUser != null)		return true;
		
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}
	
	

}
