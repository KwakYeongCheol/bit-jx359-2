package kr.co.webcash.domain;

import java.util.Date;
import java.util.List;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.comment.Comment;
import kr.co.webcash.domain.notification.Notificable;
import kr.co.webcash.domain.user.User;

import org.springframework.format.annotation.DateTimeFormat;

public class Guestbook implements Notificable{
	private long id;
	private Blog blog;
	private long displayId;
	private User writer;
	private String contents;
	
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date dateCreated;
	
	/* attach */
	private List<Comment> commentList;
	

	@Override
	public Blog getNotificationBlog() {
		return blog;
	}

	@Override
	public String getNotificationContents() {
		StringBuilder builder = new StringBuilder();
		builder.append("<a href=\"").append(getURI()).append("\">")
			.append(writer.getName()).append(" 님이 새 방명록을 남기셨습니다.")
			.append("</a>");
		return builder.toString();
	}
	
	public String getURI(){
		StringBuilder builder = new StringBuilder();
		builder.append("/").append(blog.getId()).append("/guestbook/").append(displayId);
		return builder.toString();
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
