package kr.co.webcash.domain;

public class Favorite {
	private String loginId;
	private Blog favoriteBlog;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public Blog getFavoriteBlog() {
		return favoriteBlog;
	}
	public void setFavoriteBlog(Blog favoriteBlog) {
		this.favoriteBlog = favoriteBlog;
	}
	
	
}
