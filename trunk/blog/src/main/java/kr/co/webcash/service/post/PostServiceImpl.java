package kr.co.webcash.service.post;
 
import java.util.ArrayList;
import java.util.List;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.TagCategory;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.repository.CategoryRepository;
import kr.co.webcash.repository.PostMetadataRepository;
import kr.co.webcash.repository.PostRepository;
import kr.co.webcash.repository.TagCategoryRepository;
import kr.co.webcash.service.post.scrap.ScrapService;

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
		Post findPost = postRepository.findByBlogIdAndDisplayId(post.getBlogId(), post.getDisplayId());
		
		if(post.getIsTemp()){
			post.getPostMetadata().setIsPublic(false);
		}
		if(findPost == null){
			insert(post);
		}else{
			update(post);
		}
	}
	
	private void insert(Post post){
		Post lastPost = postRepository.findLast();
		if(lastPost == null){
			post.setId(1);
		}else{
			post.setId(lastPost.getId() + 1);
		}

		postRepository.insert(post);
		post.getPostMetadata().setPost(post);
		postMetadataRepository.insert(post.getPostMetadata());
		postRevisionService.save(post);
		
		postTagService.save(post);
		scrapService.save(post);
	}

	@Override
	public void update(Post post) {
		postRevisionService.save(post);
		postRepository.update(post);
		post.getPostMetadata().setPost(post);
		postMetadataRepository.update(post.getPostMetadata());
		
		scrapService.sendNotification(post);
	}
	
	@Override
	public void delete(Post post) {
		postRepository.delete(post);
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
	
	public Page getPage(String blogId, int pageNum, int pageSize){
		return new Page(pageNum, postRepository.countPublicByBlogId(blogId), pageSize);
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
	public List<Post> search(String query, Page page) {
		return wrap(postRepository.findAllByQueryAndPage(query, page.getStartPage(), page.getPageSize()));
	}
	
	@Override
	public List<Post> searchByBlogId(String blogId, String query, Page page) {
		return wrap(postRepository.findAllByBlogIdAndQueryAndPage(blogId, query, page.getStartPage(), page.getPageSize()));
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
	public List<Post> listByBlogIdAndPageNumberAndPageSize(String blogId, int pageNumber, int pageSize) {
		return listByBlogIdAndPage(blogId, getPage(blogId, pageNumber, pageSize));
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

	@Override
	public List<Post> tempListByBlogId(String blogId) {
		return postRepository.findAllTempByBlogId(blogId);
	}
	
	@Override
	public int count() {
		return postRepository.count();
	}


	@Override
	public int countByCategory(Category category) {
		return postRepository.countByCategoryId(category.getId());
	}

	@Override
	public List<Post> listByPage(Page page) {
		return postRepository.findAllByPage(page);
	}

	@Override
	public int countByTagCategory(TagCategory tagCategory) {
		List<String> tagList = TagCategoryRepository.findAll(tagCategory);
		
		return postRepository.countByTagList(tagList);
	}

	@Override
	public List<Post> listByTagCategoryAndPage(TagCategory tagCategory, Page page) {
		List<String> tagList = TagCategoryRepository.findAll(tagCategory);
		return wrap(postRepository.findAllByTagListAndPage(tagList, page.getStartPage(), page.getPageSize()));
	}

	@Override
	public int countByQuery(String query) {
		return postRepository.countByQuery(query);
	}
	
	@Override
	public int countByBlogIdAndQuery(String blogId, String query) {
		return postRepository.countByBlogIdAndQuery(blogId, query);
	}

	@Override
	public int countPublicByCategory(Category findCategory) {
		return postRepository.countPublicByCategoryId(findCategory.getId());
	}

	@Override
	public List<Post> listByBlogIdAndCategoryDisplayIdAndPage(String blogId, long categoryDisplayId, Page page) {
		Category findCategory = categoryRepository.findByBlogIdAndDisplayId(blogId, categoryDisplayId);
		if(findCategory == null)	return new ArrayList<Post>();
		
		return wrap(postRepository.findAllByCategoryIdAndOffsetAndLimit(findCategory.getId(), page.getStartPage(), page.getPageSize()));
	}

	@Override
	public List<Post> listPublicByBlogIdAndCategoryDisplayIdAndPage(String blogId, long displayId, Page page) {
		Category findCategory = categoryRepository.findByBlogIdAndDisplayId(blogId, displayId);
		if(findCategory == null)	return new ArrayList<Post>();
		
		return wrap(postRepository.findAllPublicByCategoryIdAndOffsetAndLimit(findCategory.getId(), page.getStartPage(), page.getPageSize()));
	}
}























