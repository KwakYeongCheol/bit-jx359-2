package kr.co.webcash.domain.blog;

public class BlogWidget {
	private Blog blog;
	private boolean visitCount;
	private boolean contents;
	private boolean tag;
	
	public Blog getBlog() {		return blog;	}
	public void setBlog(Blog blog) {		this.blog = blog;	}
	public boolean getContents() {		return contents;	}
	public void setContents(boolean contents) {		this.contents = contents;	}
	public boolean getVisitCount() {		return visitCount;	}
	public void setVisitCount(boolean visitCount) {		this.visitCount = visitCount;	}
	public boolean getTag() {		return tag;	}
	public void setTag(boolean tag) {		this.tag = tag;	}
}
