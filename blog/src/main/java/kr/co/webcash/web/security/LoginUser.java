package kr.co.webcash.web.security;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Favorite;
import kr.co.webcash.domain.User;

public interface LoginUser {
	public boolean isLoggedIn();
	
	public User getLoginUser();
	
	public List<Favorite> getFavoriteList();
	public void removeFavorite(Favorite favorite);
	
	public Blog getBlog();
	public void setBlog(Blog blog);
	
	public void login(User user);
	public void logout();

}
