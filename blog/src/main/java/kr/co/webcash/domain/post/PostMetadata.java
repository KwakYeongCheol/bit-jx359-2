package kr.co.webcash.domain.post;

public class PostMetadata {
	
	private Post post;
	
	private boolean isPublic;
	private boolean canComment;
	private boolean canScrap;
	private boolean canTrackback;
	
	public PostMetadata(){
		setIsPublic(true);
		setCanComment(true);
		setCanScrap(true);
		setCanTrackback(true);
	}
	
	public PostMetadata(Post post){
		this();
		this.post = post;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ isPublic : ").append(getIsPublic())
			.append(", canComment : ").append(getCanComment())
			.append(", canScrap : ").append(getCanScrap())
			.append(", canTrackback : ").append(getCanTrackback()).append(" ]");
		
		return builder.toString();
	}
	
	public Post getPost() {		return post;	}
	public void setPost(Post post) {		this.post = post;	}
	public boolean getIsPublic() {		return isPublic;	}
	public void setIsPublic(boolean isPublic) {		this.isPublic = isPublic;	}
	public boolean getCanComment() {		return canComment;	}
	public void setCanComment(boolean canComment) {		this.canComment = canComment;	}
	public boolean getCanScrap() {		return canScrap;	}
	public void setCanScrap(boolean canScrap) {		this.canScrap = canScrap;	}
	public boolean getCanTrackback() {		return canTrackback;	}
	public void setCanTrackback(boolean canTrackback) {		this.canTrackback = canTrackback;	}
}
