package kr.co.webcash.domain.post.scrap;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.post.Post;


public class ScrapTarget {
	private Post post;
	private long postRevisionId;
	
	public ScrapTarget(){
	}
	
	public ScrapTarget(Post post, long postRevisionId) {
		setPost(post);
		setPostRevisionId(postRevisionId);
	}

	public ScrapTarget(String blogId, long postDisplayId, long postRevisionId) {
		this.post = new Post();
		post.setBlog(new Blog(blogId));
		post.setDisplayId(postDisplayId);
		this.setPostRevisionId(postRevisionId);
	}
	
	public String getUri(){
		StringBuilder builder = new StringBuilder();
		builder.append("/").append(post.getBlogId()).append("/").append(post.getDisplayId());
		
		return builder.toString();
	}

	@Override
	public String toString() {
		return "ScrapTarget [post=" + post + ", postRevisionId="
				+ postRevisionId + "]";
	}
	
	public Blog getBlog() {		return this.post.getBlog();	}
	
	public Post getPost() {		return post;	}
	public void setPost(Post post) {		this.post = post;	}
	public long getPostRevisionId() {		return postRevisionId;	}
	public void setPostRevisionId(long postRevisionId) {		this.postRevisionId = postRevisionId;	}

	public String getBlogId() {		return post.getBlogId();	}
	public String getBlogTitle() {	return post.getBlogTitle();	}
	public long getPostDisplayId() {		return post.getDisplayId();	}
	public String getPostContents() {		return post.getContents(this.postRevisionId);	}
	public String getPostTitle() {		return post.getTitle();	}

}
