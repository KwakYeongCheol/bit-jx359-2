package kr.co.webcash.web.security;

import kr.co.webcash.domain.User;

public interface LoginUser {
	
	public void save(User user);
	public void remove();
	public boolean isLoggedIn();
	public User loginUser();

}
