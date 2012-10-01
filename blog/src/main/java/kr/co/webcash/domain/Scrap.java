package kr.co.webcash.domain;


public class Scrap {
	private long postId;
	private String blogId;
	private Blog scrappedBlog;
	private long scrappedPostId;
	private String scrappedPostTitle;
	private String scrappedPostContents;
	
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public long getScrappedPostId() {
		return scrappedPostId;
	}
	public void setScrappedPostId(long scrappedPostId) {
		this.scrappedPostId = scrappedPostId;
	}
	public String getScrappedPostTitle() {
		return scrappedPostTitle;
	}
	public void setScrappedPostTitle(String scrappedPostTitle) {
		this.scrappedPostTitle = scrappedPostTitle;
	}
	public String getScrappedPostContents() {
		return scrappedPostContents;
	}
	public void setScrappedPostContents(String scrappedPostContents) {
		this.scrappedPostContents = scrappedPostContents;
	}
	public Blog getScrappedBlog() {
		return scrappedBlog;
	}
	public void setScrappedBlog(Blog scrappedBlog) {
		this.scrappedBlog = scrappedBlog;
	}
	@Override
	public String toString() {
		return "Scrap [postId=" + postId + ", blogId=" + blogId
				+ ", scrappedBlog=" + scrappedBlog + ", scrappedPostId="
				+ scrappedPostId + ", scrappedPostTitle=" + scrappedPostTitle
				+ ", scrappedPostContents=" + scrappedPostContents + "]";
	}
}
