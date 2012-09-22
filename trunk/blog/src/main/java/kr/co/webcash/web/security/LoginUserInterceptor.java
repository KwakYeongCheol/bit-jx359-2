package kr.co.webcash.web.security;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.webcash.domain.User;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginUserInterceptor extends HandlerInterceptorAdapter {
	
	@Inject private Provider<LoginUser> loginUserProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User loginUser = this.loginUserProvider.get().loginUser();
		
		if(loginUser != null)		return true;
		
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}

}
