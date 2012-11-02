package kr.co.webcash.domain;

import java.util.Date;

public class BlogVisitHistory {
	private Blog blog;
	
	private String connectIPAddress;
	
	private Date visited;
	
	public BlogVisitHistory(){
	}

	public BlogVisitHistory(Blog blog, String connectIPAddress, Date visited) {
		setBlog(blog);
		setConnectIPAddress(connectIPAddress);
		setVisited(visited);
	}
	
	public Blog getBlog() {					return blog;			}
	public void setBlog(Blog blog) {		this.blog = blog;		}
	public String getConnectIPAddress() {	return connectIPAddress;	}
	public void setConnectIPAddress(String connectIPAddress) {		this.connectIPAddress = connectIPAddress;	}
	public Date getVisited() {				return visited;			}
	public void setVisited(Date visited) {	this.visited = visited;	}

}
