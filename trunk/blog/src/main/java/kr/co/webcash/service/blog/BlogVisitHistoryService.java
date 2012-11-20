package kr.co.webcash.service.blog;

import java.util.ArrayList;
import java.util.List;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.blog.BlogVisitHistory;

public interface BlogVisitHistoryService {

	boolean save(BlogVisitHistory blogVisitHistory);

	boolean isVisitToday(BlogVisitHistory blogVisitHistory);
	long countToday(Blog blog);

	ArrayList test(String blogId);


}
