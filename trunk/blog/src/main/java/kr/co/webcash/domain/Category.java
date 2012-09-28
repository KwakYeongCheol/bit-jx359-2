package kr.co.webcash.domain;

public class Category {
	
	private long id;
	private Blog blog;
	private String title;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", blog=" + blog + ", title=" + title
				+ "]";
	}
	
	
	

}
