package kr.co.webcash.domain;

import java.util.Date;
import java.util.List;

import kr.co.webcash.domain.comment.Comment;

public class Guestbook {
	private long id;
	private Blog blog;
	private long displayId;
	private String writer;
	private String contents;
	private Date dateCreated;
	
	
	/* attach */
	private List<Comment> commentList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public long getDisplayId() {
		return displayId;
	}
	public void setDisplayId(long displayId) {
		this.displayId = displayId;
	}
	@Override
	public String toString() {
		return "Guestbook [id=" + id + ", blog=" + blog + ", displayId="
				+ displayId + ", writer=" + writer + ", contents=" + contents
				+ ", dateCreated=" + dateCreated + ", commentList="
				+ commentList + "]";
	}
	
	
}
