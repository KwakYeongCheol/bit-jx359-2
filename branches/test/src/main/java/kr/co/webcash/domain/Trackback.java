package kr.co.webcash.domain;

import java.util.Date;

public class Trackback {
	
	private String blogId;
	private long postId;
	private String url;
	private String blogName;
	private String title;
	private String excerpt;
	private Date dateCreated;
	
	public Trackback(){
	}
	
	
	
	public Trackback(String blogId, long postId, String url, String blogName, String title, String excerpt){
		setBlogId(blogId);
		setPostId(postId);
		setUrl(url);
		setBlogName(blogName);
		setTitle(title);
		setExcerpt(excerpt);
		setDateCreated(new Date(System.currentTimeMillis()));
	}
	
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
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
		return "Trackback [postId=" + postId + ", url="
				+ url + ", title=" + title + ", dateCreated=" + dateCreated
				+ "]";
	}
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}



	public String getBlogId() {
		return blogId;
	}



	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	
	
}
