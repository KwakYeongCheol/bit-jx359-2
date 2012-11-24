package kr.co.webcash.domain.notification;

import java.util.Date;

import kr.co.webcash.domain.blog.Blog;

import org.springframework.format.annotation.DateTimeFormat;

public class Notification {
	
	private long id;
	private Blog blog;
	private String uri;
	private String contents;
	
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date dateCreated;
	
	private boolean isPublic = true;
	
	public Notification(){
		dateCreated = new Date();
	}
	
	public Notification(long id, Blog blog){
		this();
		setId(id);
		setBlog(blog);
	}
	
	public long getId() {		return id;	}
	public void setId(long id) {		this.id = id;	}
	public Blog getBlog() {		return blog;	}
	public void setBlog(Blog blog) {		this.blog = blog;	}
	public String getUri() {		return uri;	}
	public void setUri(String uri) {		this.uri = uri;	}
	public String getContents() {		return contents;	}
	public void setContents(String contents) {		this.contents = contents;	}
	public Date getDateCreated() {		return dateCreated;	}
	public void setDateCreated(Date dateCreated) {		this.dateCreated = dateCreated;	}
	public boolean getIsPublic() {		return isPublic;	}
	public void setIsPublic(boolean isPublic) {		this.isPublic = isPublic;	}
}
