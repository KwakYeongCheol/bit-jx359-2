package kr.co.webcash.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String loginId;
	private String password;
	private String name;
	
	private List<Favorite> favoriteList;
	private List<Blog> blogList;
	
	public User(){
		this.favoriteList = new ArrayList<Favorite>();
	}
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "User [loginId=" + loginId + ", password=" + password
				+ ", name=" + name + "]";
	}
	
	public List<Favorite> getFavoriteList() {
		return favoriteList;
	}
	
	public void setFavoriteList(List<Favorite> favoriteList) {
		this.favoriteList = favoriteList;
	}

	public List<Blog> getBlogList() {
		return blogList;
	}

	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}
}
  