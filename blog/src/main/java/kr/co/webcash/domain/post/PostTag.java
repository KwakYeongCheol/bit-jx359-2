package kr.co.webcash.domain.post;

public class PostTag {
	private Post post;
	private String value;
	
	public PostTag(){
	}
	
	public PostTag(String value){
		setValue(value);
	}
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "post ID : " + post + ", value : " + getValue();
	}
}
