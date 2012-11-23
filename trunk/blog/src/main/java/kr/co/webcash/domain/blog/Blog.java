package kr.co.webcash.domain.blog;

import java.util.Date;

public class Blog {
	private String id;
	private String title;
	private String owner;
	private Date dateCreated;
	
	private long todayCount;
	private long totalCount;
	
	private long totalPostCount;
	private long totalGuestbookCount;
	private long totalCommentCount;
	private long totalScrapCount;
	
	public Blog(){
	}
	
	public Blog(String id) {
		setId(id);
	}
	
	public Blog(String id, String title, String owner, Date dateCreated, long totalCount){
		setId(id);
		setTitle(title);
		setOwner(owner);
		setDateCreated(dateCreated);
		setTotalCount(totalCount);
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
	
	public String getId() {		return id;	}
	public void setId(String id) {		this.id = id;	}
	public String getTitle() {		return title;	}
	public void setTitle(String title) {		this.title = title;	}
	public String getOwner() {		return owner;	}
	public void setOwner(String owner) {		this.owner = owner;	}
	public Date getDateCreated() {		return dateCreated;	}
	public void setDateCreated(Date dateCreated) {		this.dateCreated = dateCreated;	}

	public long getTodayCount() {					return todayCount;	}
	public void setTodayCount(long todayCount) {	this.todayCount = todayCount;	}
	public long getTotalCount() {					return totalCount;	}
	public void setTotalCount(long totalCount) {	this.totalCount = totalCount;	}
	
	public long getTotalPostCount() {		return totalPostCount;	}
	public void setTotalPostCount(long totalPostCount) {		this.totalPostCount = totalPostCount;	}
	public long getTotalGuestbookCount() {		return totalGuestbookCount;	}
	public void setTotalGuestbookCount(long totalGuestbookCount) {		this.totalGuestbookCount = totalGuestbookCount;	}
	public long getTotalCommentCount() {		return totalCommentCount;	}
	public void setTotalCommentCount(long totalCommentCount) {		this.totalCommentCount = totalCommentCount;	}
	public long getTotalScrapCount() {		return totalScrapCount;	}
	public void setTotalScrapCount(long totalScrapCount) {		this.totalScrapCount = totalScrapCount;	}
}
