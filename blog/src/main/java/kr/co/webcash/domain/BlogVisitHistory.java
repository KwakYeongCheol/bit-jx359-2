package kr.co.webcash.domain;

import java.util.Date;

public class BlogVisitHistory {
	private Blog blog;
	private Date visited;
	
	public BlogVisitHistory(){
	}

	public BlogVisitHistory(Blog blog, Date visited) {
		setBlog(blog);
		setVisited(visited);
	}
	
	public Blog getBlog() {					return blog;			}
	public void setBlog(Blog blog) {		this.blog = blog;		}
	public Date getVisited() {				return visited;			}
	public void setVisited(Date visited) {	this.visited = visited;	}
}
