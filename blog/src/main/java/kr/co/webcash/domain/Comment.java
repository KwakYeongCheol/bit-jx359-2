package kr.co.webcash.domain;

import java.util.Date;

public class Comment {
	private long id;
	private long displayId;
	private CommentTarget target;
	private User writer;
	private String contents;
	private Date dateCreated;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CommentTarget getTarget() {
		return target;
	}
	public void setTarget(CommentTarget target) {
		this.target = target;
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
		return "Comment [id=" + id + ", target=" + target + ", writer=" + writer
				+ ", contents=" + contents + ", dateCreated=" + dateCreated
				+ "]";
	}
	public long getDisplayId() {
		return displayId;
	}
	public void setDisplayId(long displayId) {
		this.displayId = displayId;
	}
}
