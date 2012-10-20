package kr.co.webcash.domain;

import kr.co.webcash.domain.post.Post;

public class PostRevision {
	private Post post;
	private long displayId;
	private String diff;
	
	public PostRevision(){
		
	}
	
	public PostRevision(Post post, long displayId, String diff){
		setPost(post);
		setDisplayId(displayId);
		setDiff(diff);
	}
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public long getDisplayId() {
		return displayId;
	}
	public void setDisplayId(long displayId) {
		this.displayId = displayId;
	}
	public String getDiff() {
		return diff;
	}
	public void setDiff(String diff) {
		this.diff = diff;
	}

	@Override
	public String toString() {
		return "PostRevision [post=" + post + ", displayId=" + displayId
				+ ", diff=" + diff + "]";
	}
}
