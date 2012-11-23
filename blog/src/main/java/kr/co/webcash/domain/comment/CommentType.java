package kr.co.webcash.domain.comment;

public enum CommentType{
	post("글"), guestbook("방명록");
	
	private final String value;
	
	CommentType(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public String getValue(){
		return value;
	}
}
