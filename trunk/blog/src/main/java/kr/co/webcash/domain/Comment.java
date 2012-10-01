package kr.co.webcash.domain;

import java.util.Date;

public class Comment {
	private long id;
	private Blog blog;
	private long targetId;
	private CommentType type;
	private User writer;
	private String contents;
	private Date dateCreated;
	
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
	public long getTargetId() {
		return targetId;
	}
	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}
	public CommentType getType() {
		return type;
	}
	public void setType(CommentType type) {
		this.type = type;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
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
	
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", blog=" + blog + ", targetId="
				+ targetId + ", type=" + type + ", writer=" + writer
				+ ", contents=" + contents + ", dateCreated=" + dateCreated
				+ "]";
	}
	
	
	
	
}
