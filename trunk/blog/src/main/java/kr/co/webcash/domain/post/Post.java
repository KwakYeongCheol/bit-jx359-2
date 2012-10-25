package kr.co.webcash.domain.post;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.Trackback;

public class Post {
	
	private long id;
	
	private Category category;
	
	private long displayId;
	
	private String title;
	private String contents;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date dateCreated;
	
	
	/* meta data */
	private PostMetadata postMetadata;
	private List<Comment> commentList;
	private List<Trackback> trackbackList;
	
	public Post(){}	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

//	public Scrap getScrap() {
//		return scrap;
//	}
//
//	public void setScrap(Scrap scrap) {
//		this.scrap = scrap;
//	}
	public List<Trackback> getTrackbackList() {
		return trackbackList;
	}

	public void setTrackbackList(List<Trackback> trackbackList) {
		this.trackbackList = trackbackList;
	}

	public PostMetadata getPostMetadata() {
		return postMetadata;
	}

	public void setPostMetadata(PostMetadata postMetadata) {
		this.postMetadata = postMetadata;
	}
	
	public long getDisplayId() {
		return displayId;
	}

	public void setDisplayId(long displayId) {
		this.displayId = displayId;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", category=" + category + ", displayId="
				+ displayId + ", title=" + title + ", contents=" + contents
				+ ", dateCreated=" + dateCreated + ", postMetadata="
				+ postMetadata + ", commentList="
				+ commentList + ", trackbackList=" + trackbackList + "]";
	}
	
	
	
}
