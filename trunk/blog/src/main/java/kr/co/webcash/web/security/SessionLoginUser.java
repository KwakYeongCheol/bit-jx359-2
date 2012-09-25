package kr.co.webcash.web.security;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Favorite;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.FavoriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("loginUserProvider")
@Scope("session")
public class SessionLoginUser implements LoginUser{
	
	private User loginUser;
	
	@Autowired private FavoriteService favoriteService;
	@Autowired private BlogService blogService;
	
	@Override
	public void login(User user) {
		this.loginUser = user;
		user.setFavoriteList(favoriteService.listByLoginId(user.getLoginId()));
		user.setBlog(blogService.findByUserLoginId(user.getLoginId()));
	}

	@Override
	public void logout() {
		this.loginUser = null;
	}

	@Override
	public boolean isLoggedIn() {
		return (this.loginUser != null);
	}

	@Override
	public User getLoginUser() {
		return this.loginUser;
	}

	@Override
	public List<Favorite> getFavoriteList() {
		if(!isLoggedIn())	return null;
		return this.loginUser.getFavoriteList();
	}

	@Override
	public Blog getBlog() {
		if(!isLoggedIn())	return null;
		return this.loginUser.getBlog();
	}
	
	@Override
	public void setBlog(Blog blog) {
		if(!isLoggedIn())	return;
		this.loginUser.setBlog(blog);
	}

	@Override
	public void removeFavorite(Favorite favorite) {
		if(getFavoriteList() != null){
			getFavoriteList().remove(favorite);
		}
	}

	@Override
	public void addFavorite(Favorite favorite) {
		if(getFavoriteList() != null){
			getFavoriteList().add(favorite);
		}
	}

}
