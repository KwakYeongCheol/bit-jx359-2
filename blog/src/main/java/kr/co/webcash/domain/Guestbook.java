package kr.co.webcash.domain;

import java.util.Date;
import java.util.List;

public class Guestbook {
	private String id;
	private String writer;
	private Blog blog;
	private String contents;
	private Date dateCreated;
	
	private List<Comment> commentList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}	
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	@Override
	public String toString() {
		return "Guestbook [id=" + id + ", writer=" + writer + ", blog=" + blog
				+ ", contents=" + contents + ", dateCreated=" + dateCreated
				+ ", commentList=" + commentList + "]";
	}	
}
