package kr.co.webcash.domain.post;

import java.util.Date;
import java.util.List;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.PostRevision;
import kr.co.webcash.domain.Trackback;

import org.springframework.format.annotation.DateTimeFormat;

public class Post {
	
	private long id;
	
	private Category category;
	
	private long displayId;
	
	private String title;
	private String contents;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date dateCreated;
	
	/* meta data */
	private List<PostTag> postTagList;
	
	private PostMetadata postMetadata;
	private List<PostRevision> postRevisionList;
	
	private List<Comment> commentList;
	private List<Trackback> trackbackList;
	
	public Post(){}
	
	public Post(long id, Category category, long displayId, String title, String contents, Date dateCreated){
		setId(id);
		setCategory(category);
		setDisplayId(displayId);
		setTitle(title);
		setContents(contents);
		setDateCreated(dateCreated);
	}
	
	public String getBlogId(){
		if(this.category == null)		return null;
		return this.category.getBlogId();
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", category=" + category + ", displayId="
				+ displayId + ", title=" + title + ", contents=" + contents
				+ ", dateCreated=" + dateCreated + ", postMetadata="
				+ postMetadata + ", commentList="
				+ commentList + ", trackbackList=" + trackbackList + "]";
	}
	
	/* getter / setter */
	public long getId() {		return id;	}
	public void setId(long id) {		this.id = id;	}
	public String getTitle() {		return title;	}
	public void setTitle(String title) {		this.title = title;	}
	public String getContents() {		return contents;	}
	public void setContents(String contents) {		this.contents = contents;	}
	public Date getDateCreated() {		return dateCreated;	}
	public void setDateCreated(Date dateCreated) {		this.dateCreated = dateCreated;	}
	public Category getCategory() {		return category;	}
	public void setCategory(Category category) {		this.category = category;	}
	public List<Comment> getCommentList() {		return commentList;	}
	public void setCommentList(List<Comment> commentList) {		this.commentList = commentList;	}
	public List<Trackback> getTrackbackList() {		return trackbackList;	}
	public void setTrackbackList(List<Trackback> trackbackList) {		this.trackbackList = trackbackList;	}
	public PostMetadata getPostMetadata() {		return postMetadata;	}
	public void setPostMetadata(PostMetadata postMetadata) {		this.postMetadata = postMetadata;	}
	public long getDisplayId() {		return displayId;	}
	public void setDisplayId(long displayId) {		this.displayId = displayId;	}
	public List<PostRevision> getPostRevisionList() {		return postRevisionList;	}
	public void setPostRevisionList(List<PostRevision> postRevisionList) {		this.postRevisionList = postRevisionList;	}
	public List<PostTag> getPostTagList() {		return postTagList;	}
	public void setPostTagList(List<PostTag> postTagList) {		this.postTagList = postTagList;	}
}
