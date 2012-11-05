package kr.co.webcash.domain;

public class Page {
	private int pageSize;	//한 페이지당 보여줄 글의 갯수
	private int pageGroupSize;	//페이지그룹안의 페이지 갯수
	private int currentPage;	//현재 페이지 번호
	private int startPage;  	//한 페이지의 시작글번호
	private int endPage; 	//한 페이지의 마지막글 번호
	private int count;	//전체 글의 수
	private int pageGroupCount; 	//페이지그룹의 갯수
	private int numPageGroup;	//페이지 그룹번호
	
	public Page(){
		setPageSize(10);
		setPageGroupSize(10);
	}
	
	public Page(int startPage, int endPage) {
		this();
		this.startPage = startPage;
		this.endPage = endPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageGroupSize() {
		return pageGroupSize;
	}
	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageGroupCount() {
		return pageGroupCount;
	}
	public void setPageGroupCount(int pageGroupCount) {
		this.pageGroupCount = pageGroupCount;
	}
	public int getNumPageGroup() {
		return numPageGroup;
	}
	public void setNumPageGroup(int numPageGroup) {
		this.numPageGroup = numPageGroup;
	}
	
	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", pageGroupSize="
				+ pageGroupSize + ", currentPage=" + currentPage
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", count="
				+ count + ", pageGroupCount=" + pageGroupCount
				+ ", numPageGroup=" + numPageGroup + "]";
	}
}
