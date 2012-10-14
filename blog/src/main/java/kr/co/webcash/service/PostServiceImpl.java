package kr.co.webcash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Scrap;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.repository.CategoryRepository;
import kr.co.webcash.repository.PostRepository;
import kr.co.webcash.repository.ScrapRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private ScrapRepository scrapRepository;
	private CategoryRepository categoryRepository;
	
	@Autowired
	public void setDependencies(PostRepository postRepository, 
			ScrapRepository scrapRepository, 
			CategoryRepository categoryRepository){
		
		this.postRepository = postRepository;
		this.scrapRepository = scrapRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void save(Post post) {
		Post lastPost = postRepository.findLast();
		if(lastPost == null){
			post.setId(1);
		}else{
			post.setId(lastPost.getId() + 1);
		}
		
		postRepository.insert(post);
		
		addScrap(post);
	}

	private void addScrap(Post post) {
		Scrap scrap = post.getScrap();
		if(scrap != null){
			scrap.setPostId(post.getId());
			scrapRepository.insert(scrap);
		}
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
	public List<Post> listByBlogIdAndCategoryDisplayId(String blogId, long categoryDisplayId) {
		Category category = categoryRepository.findByBlogIdAndDisplayId(blogId, categoryDisplayId);
		
		return postRepository.findAllByCategoryId(category.getId());
	}

	@Override
	public List<Post> listPublic(String blogId) {
		Map params = new HashMap();
		params.put("isPublic", "true");
		return postRepository.findAllByBlogIdAndPostMetadataParams(blogId, params);
	}

	@Override
	public List<Post> listAll(String blogId) {
		return postRepository.findAllByBlogId(blogId);
	}
	
	@Override
	public long findLastDisplayIdByBlogId(String blogId){
		Post post = postRepository.findLastByBlogId(blogId);
		
		if(post == null)	return 0;
		else				return post.getDisplayId();
	}

//	@Override
//	public Post findLastByBlogId(String blogId) {
//		return postRepository.findLastByBlogId(blogId);
//	}

	@Override
	public Post findByBlogIdAndDisplayId(String blogId, long displayId) {
		return postRepository.findByBlogIdAndDisplayId(blogId, displayId);
	}

}
