package kr.co.webcash.service.post.scrap;

import java.util.List;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.scrap.Scrap;

public interface ScrapService {
	List<Scrap> listByTargetBlog(Blog blog);

	void save(Post post);
	void save(Scrap scrap);
	void update(Post post);

	void convertFromScrapTagToScrapContents(Post post);

	void sendNotification(Post post);

	int countByBlog(Blog blog);

	List<Scrap> listByBlogAndPage(Blog blog, Page page);

	void delete(Post post);

}
