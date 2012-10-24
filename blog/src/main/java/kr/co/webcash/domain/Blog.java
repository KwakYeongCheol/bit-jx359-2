package kr.co.webcash.domain;

import java.util.Date;

public class Blog {
	private String id;
	private String title;
	private String owner;
	private Date dateCreated;
	
	private long todayCount;
	private long totalCount;
	
	public Blog(){
	}
	
	public Blog(String id) {
		setId(id);
	}
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)							return true;
		if (obj == null)							return false;
		if (getClass() != obj.getClass())			return false;
		
		Blog other = (Blog) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}

	public long getTodayCount() {					return todayCount;	}
	public void setTodayCount(long todayCount) {	this.todayCount = todayCount;	}
	public long getTotalCount() {					return totalCount;	}
	public void setTotalCount(long totalCount) {	this.totalCount = totalCount;	}
}
