package kr.co.webcash.domain.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.co.webcash.domain.Favorite;
import kr.co.webcash.domain.blog.Blog;

public class User {
	private String loginId;
	private String password;

	private String lastName;
	private String firstName;
	private String name;
	
	private Sex sex;
	
	private Date birthday;
	
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		setName(getLastName() + getFirstName());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		setName(getLastName() + getFirstName());
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [loginId=" + loginId + ", password=" + password
				+ ", lastName=" + lastName + ", firstName=" + firstName
				+ ", name=" + name + ", sex=" + sex + ", birthday=" + birthday
				+ ", favoriteList=" + favoriteList + ", blogList=" + blogList
				+ "]";
	}
}
  