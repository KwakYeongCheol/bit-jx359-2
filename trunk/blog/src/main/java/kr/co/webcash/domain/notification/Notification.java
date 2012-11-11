package kr.co.webcash.domain.notification;

import java.util.Date;

import kr.co.webcash.domain.blog.Blog;

public class Notification {
	
	private long id;
	private Blog blog;
	private String uri;
	private String contents;
	private Date dateCreated;
	
	public Notification(){
		dateCreated = new Date();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
