package kr.co.webcash.domain.post;

import kr.co.webcash.utils.PostRevisionUtils;

public class PostRevision {
	public static final long START_REVISION_DISPLAY_ID = 1;
	
	private Post post;
	private long displayId;
	private String diff;
	
	public PostRevision(){	}
	
	public PostRevision(Post post, long displayId, String diff){
		setPost(post);
		setDisplayId(displayId);
		setDiff(diff);
	}
	
	public static String diff(String original, String revised){
		return PostRevisionUtils.generateDiff(original, revised);
	}
	
	public static String patch(String contents, String diff){
		return PostRevisionUtils.patch(contents, diff);
	}
	
	public static String compare(String original, String revised){
		return PostRevisionUtils.generateDiffRows(original, revised);
	}
	
	@Override
	public String toString() {
		return "PostRevision [post=" + post + ", displayId=" + displayId
				+ ", diff=" + diff + "]";
	}
	
	public Post getPost() {		return post;	}
	public void setPost(Post post) {		this.post = post;	}
	public long getDisplayId() {		return displayId;	}
	public void setDisplayId(long displayId) {		this.displayId = displayId;	}
	public String getDiff() {		return diff;	}
	public void setDiff(String diff) {		this.diff = diff;	}
}
