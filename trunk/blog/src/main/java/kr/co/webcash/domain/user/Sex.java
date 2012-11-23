package kr.co.webcash.domain.user;

public enum Sex {
	male("남자"), female("여자");
	
	private final String value;
	
	Sex(String value){
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
