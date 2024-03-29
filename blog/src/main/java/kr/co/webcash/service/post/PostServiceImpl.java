package kr.co.webcash.service.post;
 
import java.util.ArrayList;
import java.util.List;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.TagCategory;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.scrap.Scrap;
import kr.co.webcash.repository.CategoryRepository;
import kr.co.webcash.repository.PostMetadataRepository;
import kr.co.webcash.repository.PostRepository;
import kr.co.webcash.repository.TagCategoryRepository;
import kr.co.webcash.service.TrackbackService;
import kr.co.webcash.service.comment.CommentService;
import kr.co.webcash.service.post.scrap.ScrapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
	@Autowired private PostRevisionService postRevisionService;
	@Autowired private PostTagService postTagService;
	@Autowired private ScrapService scrapService;
	@Autowired private CommentService commentService;
	@Autowired private TrackbackService trackbackService;
	
	@Autowired private PostMetadataRepository postMetadataRepository;
	@Autowired private PostRepository postRepository;
	@Autowired private CategoryRepository categoryRepository;
	
	@Override
	public void save(Post post) {
		Post findPost = postRepository.findByBlogIdAndDisplayId(post.getBlogId(), post.getDisplayId());
		
		removeBlank(post);
		post.setContents(post.getContents().replaceAll("</p>\n", "</p>"));
		post.setContents(post.getContents().replaceAll("</p>", "</p>\n"));
		
		if(post.getIsTemp()){
			post.getPostMetadata().setIsPublic(false);
		}
		if(findPost == null){
			insert(post);
		}else{
			update(post);
		}
	}
	
	private void removeBlank(Post post){
		StringBuilder builder = new StringBuilder(post.getContents().trim());
		
		int fromIndex = -1;
		while((fromIndex = builder.indexOf("<")) != -1){
			builder.replace(0, fromIndex, builder.substring(0, fromIndex).trim());
			int toIndex = builder.indexOf(">", fromIndex);
			
			if(toIndex != -1){
				int nextToIndex = builder.indexOf("<", toIndex);
				
				if(nextToIndex != -1){
					builder.replace(toIndex+1, nextToIndex, builder.substring(toIndex+1, nextToIndex).trim());
				}
				
				builder.replace(fromIndex, toIndex+1, "");
			}
		}
		
		post.setContentsWithoutTag(builder.toString());
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
		
		postTagService.update(post);
		scrapService.update(post);
		
		scrapService.sendNotification(post);
	}
	
	@Override
	public void updateScrap(Post findPost, String targetBlogId, long targetPostDisplayId, long targetRevisionId) {
		Post currentPost = postRepository.findById(findPost.getId());
		Post targetPost = postRepository.findByBlogIdAndDisplayId(targetBlogId, targetPostDisplayId);
		
		if(targetPost != null && targetPost.getCanScrap()){
			if(targetPost.getCurrentRevision().getDisplayId() > targetRevisionId){
				String myPostTag = Scrap.getTag(targetBlogId, targetPostDisplayId, targetRevisionId);
				String updatedPostTag = Scrap.getTag(targetBlogId, targetPostDisplayId, targetPost.getCurrentRevision().getDisplayId());
				
				System.out.println(myPostTag);
				System.out.println(updatedPostTag);
				
				System.out.println(currentPost.getContents());
				currentPost.setContents(currentPost.getContents().replaceAll(myPostTag, updatedPostTag));
				System.out.println(currentPost.getContents());
			}
		}
		save(currentPost);
	}
	
	
	@Override
	public void delete(Post post) {
		scrapService.delete(post);
		postMetadataRepository.delete(post);
		postRevisionService.delete(post);
		postTagService.delete(post);
		commentService.delete(post);
		trackbackService.delete(post);
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
	public Post findByBlogIdAndDisplayIdWithOutWrap(String blogId, long displayId) {
		return postRepository.findByBlogIdAndDisplayId(blogId, displayId);
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
	public int countPublic() {
		return postRepository.countPublic();
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
	public List<Post> listPublicByPage(Page page) {
		return postRepository.findAllPublicByPage(page.getStartPage(), page.getPageSize());
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
	public int countPublicByBlogId(String blogId) {
		return postRepository.countPublicByBlogId(blogId);
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

	@Override
	public int countByBlogId(String blogId) {
		return postRepository.countByBlogId(blogId);
	}
}























