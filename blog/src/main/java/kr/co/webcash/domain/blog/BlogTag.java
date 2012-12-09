package kr.co.webcash.domain.blog;

public class BlogTag {
	private String value;
	private int totalCount;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ BlogTag : ").append(value).append(", ").append(totalCount).append(" ]");
		return builder.toString();
	}
	
	public String getValue() {		return value;	}
	public void setValue(String value) {		this.value = value;	}
	public int getTotalCount() {		return totalCount;	}
	public void setTotalCount(int totalCount) {		this.totalCount = totalCount;	}
}
