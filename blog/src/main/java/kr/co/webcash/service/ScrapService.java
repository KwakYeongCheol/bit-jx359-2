package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Scrap;
import kr.co.webcash.domain.post.Post;

public interface ScrapService {
	List<Scrap> listByTargetBlog(Blog blog);

	void save(Post post);
	void save(Scrap scrap);

	void convertFromScrapTagToScrapContents(Post post);
}
