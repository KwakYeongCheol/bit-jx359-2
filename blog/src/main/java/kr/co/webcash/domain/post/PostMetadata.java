package kr.co.webcash.domain.post;

public class PostMetadata {
	
	private Post post;
	
	private boolean isPublic;
	private boolean canScrap;
	private boolean canTrackback;
	
	public PostMetadata(){
	}
	
	public PostMetadata(Post post){
		this.post = post;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isCanScrap() {
		return canScrap;
	}

	public void setCanScrap(boolean canScrap) {
		this.canScrap = canScrap;
	}

	public boolean isCanTrackback() {
		return canTrackback;
	}

	public void setCanTrackback(boolean canTrackback) {
		this.canTrackback = canTrackback;
	}
	
}
