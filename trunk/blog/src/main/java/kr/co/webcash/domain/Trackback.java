package kr.co.webcash.domain;

import java.util.Date;

public class Trackback {
	
	private String blogId;
	private String postId;
	private String url;
	private String title;
	private Date dateCreated;
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Override
	public String toString() {
		return "Trackback [blogId=" + blogId + ", postId=" + postId + ", url="
				+ url + ", title=" + title + ", dateCreated=" + dateCreated
				+ "]";
	}

	
	
}
