package kr.co.webcash.web.security;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.webcash.utils.URLUtils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginUserInterceptor extends HandlerInterceptorAdapter {
	
	@Inject private Provider<LoginUser> loginUserProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(this.loginUserProvider.get().isLoggedIn())		return true;
		
		String requestURL = URLUtils.getRequestURL(request.getRequestURI(), request.getContextPath());
		
		response.sendRedirect(request.getContextPath() + "/login?redirectURI=" + requestURL);
		return false;
	}
}
