package kr.co.webcash.domain;

public class CommentTarget {
	private long id;
	private CommentType type;
	
	public CommentTarget(){}
	
	public CommentTarget(long id, CommentType type) {
		setId(id);
		setType(type);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CommentType getType() {
		return type;
	}
	public void setType(CommentType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "CommentTarget [id=" + id + ", type=" + type + "]";
	}
}
