package kr.co.webcash.domain.post;

import java.util.Date;
import java.util.List;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Trackback;
import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.comment.Comment;

import org.springframework.format.annotation.DateTimeFormat;

public class Post {
	
	private long id;
	
	private Category category;
	
	private long displayId;
	
	private String title;
	private String contents;
	
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm")
	private Date dateCreated;
	
	/* meta data */
	private List<PostTag> postTagList;
	
	private PostMetadata postMetadata;
	private List<PostRevision> postRevisionList;
	
	private List<Comment> commentList;
	private List<Trackback> trackbackList;
	
	public Post(){
		this.category = new Category();
		this.postMetadata = new PostMetadata(this);
	}
	
	public Post(long id, Category category, long displayId, String title, String contents, Date dateCreated){
		setId(id);
		setCategory(category);
		setDisplayId(displayId);
		setTitle(title);
		setContents(contents);
		setDateCreated(dateCreated);
	}
	
	
	public String getContents(final long revisionDisplayId){
		String contents = this.contents;
		
		for(PostRevision postRevision : postRevisionList){
			if(postRevision.getDisplayId() < revisionDisplayId)		return contents;		
			
			contents = PostRevision.patch(contents, postRevision.getDiff());
		}
		
		return contents;
	}
	
	public String getCompareContents(final long revisionDisplayId){
		return PostRevision.compare(getContents(revisionDisplayId), getContents(revisionDisplayId - 1));
	}
	
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", category=" + category + ", displayId="
				+ displayId + ", title=" + title + ", contents=" + contents
				+ ", dateCreated=" + dateCreated + ", postTagList="
				+ postTagList + ", postMetadata=" + postMetadata
				+ ", postRevisionList=" + postRevisionList + ", commentList="
				+ commentList + ", trackbackList=" + trackbackList + "]";
	}

	/* sub object's getter / setter wrapper */
	public void setBlog(Blog blog) {		this.category.setBlog(blog);	}
	public String getBlogId(){		return this.category.getBlogId();	}
	public String getBlogTitle(){	return this.category.getBlogTitle();	}
	public PostRevision getCurrentRevision(){		return postRevisionList.get(0);	}

	public boolean getCanTrackback(){		return postMetadata.getCanTrackback();	}
	public boolean getCanComment(){			return postMetadata.getCanComment();	}
	public boolean getCanScrap(){			return postMetadata.getCanScrap();		}
	public boolean getIsPublic(){			return postMetadata.getIsPublic();		}
	public boolean getIsTemp(){				return postMetadata.getIsTemp();		}
	
	
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
