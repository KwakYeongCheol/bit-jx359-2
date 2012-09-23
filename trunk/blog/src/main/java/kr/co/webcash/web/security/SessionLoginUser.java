package kr.co.webcash.web.security;

import kr.co.webcash.domain.User;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("loginUserProvider")
@Scope("session")
public class SessionLoginUser implements LoginUser{
	
	private User loginUser;
	
	@Override
	public String getLoginId(){
		if(isLoggedIn())	return this.loginUser.getLoginId();
		else				throw new IllegalStateException();
	}
	
	@Override
	public void save(User user) {
		this.loginUser = user;
	}

	@Override
	public void remove() {
		if(this.loginUser == null)		throw new IllegalStateException();
		this.loginUser = null;
	}

	@Override
	public boolean isLoggedIn() {
		return (this.loginUser != null);
	}

	@Override
	public User loginUser() {
		return this.loginUser;
	}

}
