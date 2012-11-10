package kr.co.webcash.domain.comment;

import kr.co.webcash.domain.Blog;

public class CommentTarget {
	private Blog blog;
	private long id;
	private long displayId;
	private CommentType type;
	
	public CommentTarget(){}
	
	public CommentTarget(Blog blog, long id, long displayId, CommentType type) {
		setBlog(blog);
		setId(id);
		setDisplayId(displayId);
		setType(type);
	}
	
	public String getUri() {
		StringBuilder builder = new StringBuilder();
		builder.append("/").append(blog.getId()).append("/");
		
		if(type.equals(CommentType.guestbook)){
			builder.append("guestbook/");
		}
		
		builder.append(displayId);
		return builder.toString();
	}
	
	@Override
	public String toString() {
		return "CommentTarget [blog=" + blog + ", id=" + id + ", displayId="
				+ displayId + ", type=" + type + "]";
	}

	public long getId() {		return id;	}
	public void setId(long id) {		this.id = id;	}
	public CommentType getType() {		return type;	}
	public void setType(CommentType type) {		this.type = type;	}
	public Blog getBlog() {		return blog;	}
	public void setBlog(Blog blog) {		this.blog = blog;	}
	public long getDisplayId() {		return displayId;	}
	public void setDisplayId(long displayId) {		this.displayId = displayId;	}

}
