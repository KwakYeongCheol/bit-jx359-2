package kr.co.webcash.domain;

import java.util.List;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.post.Post;

public class Category {
	private static final long DEFAULT_DISPLAY_ID = 1;
	private static final String DEFAULT_TITLE = "분류 없음";
	private static final long DEFAULT_DISTANCE = 100000000;
	
	private long id;
	
	private long displayId;
	private Blog blog;
	private String title;
	private long orderValue;

	private List<Post> postList;
	public Category(){}
	
	public Category(Blog blog, long displayId, String title) {
		setBlog(blog);
		setDisplayId(displayId);
		setTitle(title);
	}
	
	public static Category defaultCategory(Blog blog){
		return new Category(blog, DEFAULT_DISPLAY_ID, DEFAULT_TITLE);
	}
	
	public static boolean isDefaultCategory(Category category){
		if(category != null && category.getDisplayId() == DEFAULT_DISPLAY_ID)	return true;
		return false;
	}
	
	public String getBlogId() {
		if(this.blog == null)		return null;
		return this.blog.getId();
	}
	
	public static long nextOrderValue(long orderValue) {
		return orderValue + DEFAULT_DISTANCE;
	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", displayId=" + displayId + ", blog="
				+ blog + ", title=" + title + "]";
	}
	
	
	public long getId() {		return id;	}
	public void setId(long id) {		this.id = id;	}
	public long getDisplayId() {		return displayId;	}
	public void setDisplayId(long displayId) {		this.displayId = displayId;	}
	public Blog getBlog() {		return blog;	}
	public void setBlog(Blog blog) {		this.blog = blog;	}
	public String getTitle() {		return title;	}
	public void setTitle(String title) {		this.title = title;	}
	public long getOrderValue() {		return orderValue;	}
	public void setOrderValue(long orderValue) {		this.orderValue = orderValue;	}
	public List<Post> getPostList() { return postList; }
	public void setPostList(List<Post> postList) {	this.postList = postList;	}

}
