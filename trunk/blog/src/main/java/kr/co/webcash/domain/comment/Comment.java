package kr.co.webcash.domain.comment;

import java.util.Date;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.notification.Notificable;
import kr.co.webcash.domain.user.User;

import org.springframework.format.annotation.DateTimeFormat;

public class Comment implements Notificable{
	private long id;
	private long displayId;
	private CommentTarget target;
	private User writer;
	private String contents;

	@DateTimeFormat(iso= DateTimeFormat.ISO.NONE)

	private Date dateCreated;
	
	
	@Override
	public Blog getNotificationBlog() {
		return target.getBlog();
	}
	
	@Override
	public String getNotificationURI() {
		return target.getUri();
	}
	
	@Override
	public String getNotificationContents() {
		StringBuilder builder = new StringBuilder();
		builder.append(writer.getName()).append("님이 댓글을 남겼습니다.");
		return builder.toString();
	}
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", target=" + target + ", writer=" + writer
				+ ", contents=" + contents + ", dateCreated=" + dateCreated
				+ "]";
	}
	
	public String getTargetUri(){		return target.getUri();	}
	public long getTargetDisplayId(){	return target.getDisplayId();	}
	public CommentType getTargetType(){		return target.getType();		}
	
	
	/* getter / setter */
	public long getId() {		return id;	}
	public void setId(long id) {		this.id = id;	}
	public CommentTarget getTarget() {		return target;	}
	public void setTarget(CommentTarget target) {		this.target = target;	}
	public User getWriter() {		return writer;	}
	public void setWriter(User writer) {		this.writer = writer;	}
	public String getContents() {		return contents;	}
	public void setContents(String contents) {		this.contents = contents;	}
	public Date getDateCreated() {		return dateCreated;	}
	public void setDateCreated(Date dateCreated) {		this.dateCreated = dateCreated;	}
	public long getDisplayId() {		return displayId;	}
	public void setDisplayId(long displayId) {		this.displayId = displayId;	}


}
