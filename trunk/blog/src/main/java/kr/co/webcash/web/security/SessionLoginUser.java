package kr.co.webcash.web.security;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Favorite;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.FavoriteService;
import kr.co.webcash.service.blog.BlogService;

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
		user.setBlogList(blogService.findAllByUserLoginId(user.getLoginId()));
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

	public List<Blog> getBlogList(){
		if(!isLoggedIn())	return null;
		return this.loginUser.getBlogList();
	}
	
	public void setBlogList(List<Blog> blogList){
		if(!isLoggedIn())	return;
		this.loginUser.setBlogList(blogList);
	}
	
	@Override
	public void addBlog(Blog blog) {
		if(!isLoggedIn())	return;
		
		List<Blog> blogList = this.loginUser.getBlogList();
		
		boolean isUpdate = false;
		for(Blog one : blogList){
			if(one.getId().equals(blog.getId())){
				blogList.remove(one);
				blogList.add(blog);
				isUpdate = true;
			}
		}
		
		if(!isUpdate)	blogList.add(blog);
	}
	
	@Override
	public Blog getBlog() {
		if(!isLoggedIn())	return null; 
		List<Blog> blogList = this.loginUser.getBlogList();
		if(blogList != null && blogList.size() > 0){
			return blogList.get(0);
		}
		
		return null;
	}

	@Override
	public boolean isMyBlog(String blogId) {
		if(!isLoggedIn())	return false;
		for(Blog blog : this.loginUser.getBlogList()){
			if(blog.getId().equals(blogId))		return true;
		}
		
		return false;
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

	@Override
	public boolean isExistFavorite(String blogId) {
		for(Favorite favorite : getFavoriteList()){
			if(favorite.getBlog().getId().equals(blogId))		return true;
		}
		
		return false;
	}

}
