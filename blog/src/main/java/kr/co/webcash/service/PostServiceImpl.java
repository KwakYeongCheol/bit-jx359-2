package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Post;
import kr.co.webcash.domain.Scrap;
import kr.co.webcash.repository.PostRepository;
import kr.co.webcash.repository.ScrapRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	@Autowired private PostRepository postRepository;
	@Autowired private ScrapRepository scrapRepository;

	@Override
	public long findLastIdByBlogId(String blogId) {
		Post post = postRepository.findLastPostByBlogId(blogId);
		
		if(post==null){
			return 0;
		}

		return post.getId();
	}

	@Override
	public void save(Post post) {
		postRepository.insert(post);
		Scrap scrap = post.getScrap();
		if(scrap != null){
			scrap.setBlogId(post.getBlog().getId());
			scrap.setPostId(post.getId());
			scrapRepository.insert(scrap);
		}
	}

	@Override
	public List<Post> listByBlogId(String blogId) {
		return postRepository.findAllByBlogId(blogId);
	}

	@Override
	public Post findByIdAndBlogId(long id, String blogId) {
	    return postRepository.findByIdAndBlogId(id, blogId);
		
	}

	@Override
	public void update(Post post) {
		postRepository.update(post);
	}

	@Override
	public void delete(Post post) {
		postRepository.delete(post);
	}

	@Override
	public List<Post> listByBlogIdAndCategoryId(String blogId, long categoryId) {
		return postRepository.findAllByBlogIdAndCategoryId(blogId, categoryId);	}

}
