package kr.co.webcash.domain.blog;

public enum BlogTheme {
	defaultTheme("#675C47"), 
	black("#000");
	
	private String backgroundColor;
	
	BlogTheme(String backgroundColor){
		this.backgroundColor = backgroundColor;
	}
	
	@Override
	public String toString() {		return super.toString();	}
	
	public String getBackgroundColor(){	return backgroundColor;	}
}
