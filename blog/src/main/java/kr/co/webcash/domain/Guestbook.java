package kr.co.webcash.domain;

import java.util.Date;
import java.util.List;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.comment.Comment;
import kr.co.webcash.domain.notification.Notificable;
import kr.co.webcash.domain.user.User;
import kr.co.webcash.service.notification.NotificationService;

public class Guestbook implements Notificable{
	private long id;
	private Blog blog;
	private long displayId;
	private User writer;
	private String contents;
	private Date dateCreated;
	
	/* attach */
	private List<Comment> commentList;
	

	@Override
	public Blog getNotificationBlog() {
		return blog;
	}


	@Override
	public String getNotificationURI() {
		return "/"+blog.getId()+"/guestbook/"+displayId;
	}


	@Override
	public String getNotificationContents() {
		return writer.getName()+"님이 새 방명록을 남기셨습니다.";
	}
	
	@Override
	public String toString() {
		return "Guestbook [id=" + id + ", blog=" + blog + ", displayId="
				+ displayId + ", writer=" + writer + ", contents=" + contents
				+ ", dateCreated=" + dateCreated + ", commentList="
				+ commentList + "]";
	}
	
	
	public long getId() {		return id;	}
	public void setId(long id) {		this.id = id;	}
	public User getWriter(){		return writer;	}
	public void setWriter(User writer){		this.writer = writer;	}
	public String getContents() {		return contents;	}
	public void setContents(String contents) {		this.contents = contents;	}
	public Date getDateCreated() {		return dateCreated;	}
	public void setDateCreated(Date dateCreated) {		this.dateCreated = dateCreated;	}
	public Blog getBlog() {		return blog;	}
	public void setBlog(Blog blog) {		this.blog = blog;	}	
	public List<Comment> getCommentList() {		return commentList;	}
	public void setCommentList(List<Comment> commentList) {		this.commentList = commentList;	}
	public long getDisplayId() {		return displayId;	}
	public void setDisplayId(long displayId) {		this.displayId = displayId;	}


}
