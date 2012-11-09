package kr.co.webcash.domain;

public class Page {
	private int pageSize;			//한 페이지당 보여줄 글의 갯수
	private int pageGroupSize;		//페이지그룹안의 페이지 갯수
	private int currentPage;	//현재 페이지 번호
	private int startPage;  	//한 페이지의 시작글번호
	private int endPage; 	//한 페이지의 마지막글 번호
	private int count;	//전체 글의 수
	private int pageGroupCount; 	//페이지그룹의 갯수
	private int numPageGroup;	//페이지 그룹번호
	
	public Page(int currentPage, int totalCount) {
		this(currentPage, totalCount, 10, 10);
	}
	
	public Page(int currentPage, int totalCount, int pageSize, int pageGroupSize){
		this.currentPage = currentPage;
		this.count = totalCount;
		this.pageSize = pageSize;
		this.pageGroupSize = pageGroupSize;

		init();
	}
	
	private void init(){
		this.startPage = (currentPage - 1) * pageSize;
		this.endPage = currentPage * pageSize;
		this.pageGroupCount = count/(pageSize * pageGroupSize) + (count%(pageSize * pageGroupSize) == 0 ? 0 : 1);
		this.numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize);
	}
	
	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", pageGroupSize="
				+ pageGroupSize + ", currentPage=" + currentPage
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", count="
				+ count + ", pageGroupCount=" + pageGroupCount
				+ ", numPageGroup=" + numPageGroup + "]";
	}
	
	public int getPageSize() {		return pageSize;	}
	public void setPageSize(int pageSize) {		this.pageSize = pageSize;	}
	public int getPageGroupSize() {		return pageGroupSize;	}
	public void setPageGroupSize(int pageGroupSize) {		this.pageGroupSize = pageGroupSize;	}
	public int getCurrentPage() {		return currentPage;	}
	public void setCurrentPage(int currentPage) {		this.currentPage = currentPage;	}
	public int getStartPage() {		return startPage;	}
	public void setStartPage(int startPage) {		this.startPage = startPage;	}
	public int getEndPage() {		return endPage;	}
	public void setEndPage(int endPage) {		this.endPage = endPage;	}
	public int getCount() {		return count;	}
	public void setCount(int count) {		this.count = count;	}
	public int getPageGroupCount() {		return pageGroupCount;	}
	public void setPageGroupCount(int pageGroupCount) {		this.pageGroupCount = pageGroupCount;	}
	public int getNumPageGroup() {		return numPageGroup;	}
	public void setNumPageGroup(int numPageGroup) {		this.numPageGroup = numPageGroup;	}
}
