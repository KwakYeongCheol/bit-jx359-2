package kr.co.webcash.domain.blog;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BlogVisitHistory {
	private Blog blog;
	
	private String connectIPAddress;
	
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
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
