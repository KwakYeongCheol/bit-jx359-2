package kr.co.webcash.web.security;

import java.util.List;

import kr.co.webcash.domain.Favorite;
import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.user.User;

public interface LoginUser {
	public boolean isLoggedIn();
	
	public User getLoginUser();
	
	public List<Favorite> getFavoriteList();
	public void removeFavorite(Favorite favorite);
	public void addFavorite(Favorite favorite);
	public boolean isExistFavorite(String blogId);
	
	public List<Blog> getBlogList();
	public void setBlogList(List<Blog> blogList);
	public void addBlog(Blog blog);
	public Blog getBlog();
	public boolean isMyBlog(String blogId);
	
	public void login(User user);
	public void logout();


}
