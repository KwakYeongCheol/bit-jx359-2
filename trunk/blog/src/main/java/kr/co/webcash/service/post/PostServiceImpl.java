package kr.co.webcash.service.post;
 
import java.util.List;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.PostMetadata;
import kr.co.webcash.repository.CategoryRepository;
import kr.co.webcash.repository.PostMetadataRepository;
import kr.co.webcash.repository.PostRepository;
import kr.co.webcash.service.ScrapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
	@Autowired private PostRevisionService postRevisionService;
	@Autowired private PostTagService postTagService;
	
	@Autowired private ScrapService scrapService;
	
	@Autowired private PostRepository postRepository;
	
	@Autowired private PostMetadataRepository postMetadataRepository;
	@Autowired private CategoryRepository categoryRepository;
	
	@Override
	public void save(Post post) {
		Post lastPost = postRepository.findLast();
		if(lastPost == null){
			post.setId(1);
		}else{
			post.setId(lastPost.getId() + 1);
		}

		postRepository.insert(post);
		insertPostMetadata(post);
		postRevisionService.save(post);
		
		postTagService.save(post);
		scrapService.save(post);
	}

	@Override
	public void update(Post post) {
		postRevisionService.save(post);
		postRepository.update(post);
	}
	
	@Override
	public void delete(Post post) {
		postRepository.delete(post);
	}
	
	private void insertPostMetadata(Post post){
		PostMetadata metadata = post.getPostMetadata();
		metadata.setPost(post);
		
		postMetadataRepository.insert(metadata);
	}
	
	private List<Post> wrap(List<Post> postList){
		for(Post post : postList){
			wrap(post);
		}
		
		return postList;
	}
	
	private Post wrap(Post post){
		if(post == null)	return null;
		
		convertFromScrapTagToScrapContents(post);
		return post;
	}
	
	private void convertFromScrapTagToScrapContents(Post post){
		scrapService.convertFromScrapTagToScrapContents(post);
	}
	
	@Override
	public Page getPage(String blogId, int pageNum) {
		return new Page(pageNum, postRepository.countByBlogId(blogId));
	}

	@Override
	public Page getPagePublic(String blogId, int pageNum) {
		return new Page(pageNum, postRepository.countPublicByBlogId(blogId));
	}
	
	@Override
	public long findLastDisplayIdByBlogId(String blogId){
		Post post = postRepository.findLastByBlogId(blogId);
		
		if(post == null)	return 0;
		else				return post.getDisplayId();
	}
	
	@Override
	public List<Post> listByBlogIdAndCategoryDisplayId(String blogId, long categoryDisplayId) {
		Category category = categoryRepository.findByBlogIdAndDisplayId(blogId, categoryDisplayId);
		return wrap(postRepository.findAllByCategoryId(category.getId()));
	}
	
	@Override
	public Post findById(long id) {
		return wrap(postRepository.findById(id));
	}

	@Override
	public Post findByBlogIdAndDisplayId(String blogId, long displayId) {
		return wrap(postRepository.findByBlogIdAndDisplayId(blogId, displayId));
	}

	@Override
	public List<Post> search(String query) {
		return wrap(postRepository.findAllByQuery(query));
	}

	@Override
	public List<Post> listByBlogId(String blogId) {
		return wrap(postRepository.findAllByBlogId(blogId));
	}
	
	@Override
	public List<Post> listByBlogIdAndPageNumber(String blogId, int pageNumber) {
		return wrap(listByBlogIdAndPage(blogId, getPage(blogId, pageNumber)));
	}
	
	@Override
	public List<Post> listByBlogIdAndPage(String blogId, Page page) {
		return wrap(postRepository.findAllByBlogIdAndPage(blogId, page.getStartPage(), page.getPageSize()));
	}

	@Override
	public List<Post> listPublicByBlogIdAndPageNumber(String blogId, int pageNumber) {
		return wrap(listPublicByBlogIdAndPage(blogId, getPage(blogId, pageNumber)));
	}
	
	@Override
	public List<Post> listPublicByBlogIdAndPage(String blogId, Page page) {
		return wrap(postRepository.findAllPublicByBlogIdAndPage(blogId, page.getStartPage(), page.getPageSize()));
	}
}
