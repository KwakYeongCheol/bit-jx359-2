package kr.co.webcash.service.post;

import java.util.List;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.TagCategory;
import kr.co.webcash.domain.post.Post;

public interface PostService {
	void save(Post post);
	void update(Post post);
	void delete(Post post);

	long findLastDisplayIdByBlogId(String blogId);
	
	int count();
	int countByCategory(Category category);
	int countByTagCategory(TagCategory tagCategory);
	int countByQuery(String query);
	
	Post findById(long id);
	Post findByBlogIdAndDisplayId(String blogId, long displayId);

	Page getPage(String blogId, int pageNum);
	Page getPagePublic(String blogId, int pageNum);
	
	List<Post> search(String query, Page page);
	List<Post> listByPage(Page page);
	
	List<Post> listByBlogIdAndCategoryDisplayId(String blogId, long categoryDisplayId);
	List<Post> listByBlogId(String blogId);
	List<Post> listByBlogIdAndPageNumber(String blogId, int pageNumber);
	List<Post> listByBlogIdAndPage(String blogId, Page page);
	
	List<Post> listPublicByBlogIdAndPageNumber(String blogId, int pageNumber);
	List<Post> listPublicByBlogIdAndPage(String blogId, Page page);
	List<Post> listByBlogIdAndPageNumberAndPageSize(String blogId, int pageNumber, int pageSize);
	List<Post> tempListByBlogId(String blogId);
	List<Post> listByTagCategoryAndPage(TagCategory tagCategory, Page page);
}
