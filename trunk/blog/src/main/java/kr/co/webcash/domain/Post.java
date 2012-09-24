package kr.co.webcash.domain;

import java.util.Date;
import java.util.List;

public class Post {
	private String id;
	private Blog blog;
	private Category category;
	private String title;
	private String contents;
	private Date dateCreated;
	
	private List<Comment> commentList;
	
	public Post(){}	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", blog=" + blog + ", title=" + title
				+ ", contents=" + contents + ", dateCreated=" + dateCreated
				+ "]";
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}	
	
	
	
}
