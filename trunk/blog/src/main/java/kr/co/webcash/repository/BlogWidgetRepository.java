package kr.co.webcash.repository;

import kr.co.webcash.domain.blog.BlogWidget;

public interface BlogWidgetRepository {

	BlogWidget findByBlogId(String blogId);

	void insert(BlogWidget blogWidget);

	void update(BlogWidget blogWidget);

}
