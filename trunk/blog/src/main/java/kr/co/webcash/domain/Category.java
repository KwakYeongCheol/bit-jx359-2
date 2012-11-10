package kr.co.webcash.domain;

public class Category {
	private long id;
	
	private long displayId;
	private Blog blog;
	private String title;

	public Category(){}
	
	public Category(Blog blog, int displayId, String title) {
		setBlog(blog);
		setDisplayId(displayId);
		setTitle(title);
	}
	
	public static Category defaultCategory(Blog blog){
		return new Category(blog, 1, "분류 없음");
	}
	
	public String getBlogId() {
		if(this.blog == null)		return null;
		return this.blog.getId();
	}
	
	public long getId() {		return id;	}
	public void setId(long id) {		this.id = id;	}
	public long getDisplayId() {		return displayId;	}
	public void setDisplayId(long displayId) {		this.displayId = displayId;	}
	public Blog getBlog() {		return blog;	}
	public void setBlog(Blog blog) {		this.blog = blog;	}
	public String getTitle() {		return title;	}
	public void setTitle(String title) {		this.title = title;	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", displayId=" + displayId + ", blog="
				+ blog + ", title=" + title + "]";
	}
}
