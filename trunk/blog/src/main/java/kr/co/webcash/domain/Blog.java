package kr.co.webcash.domain;

import java.util.Date;

public class Blog {
	private String id;
	private String title;
	private String owner;
	private Date dateCreated;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", owner=" + owner
				+ ", dateCreated=" + dateCreated + "]";
	}
	
	
	

}
