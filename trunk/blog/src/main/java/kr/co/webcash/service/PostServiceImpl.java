package kr.co.webcash.service;
 
import java.util.List;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.Scrap;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.PostMetadata;
import kr.co.webcash.domain.post.PostTag;
import kr.co.webcash.repository.CategoryRepository;
import kr.co.webcash.repository.PostMetadataRepository;
import kr.co.webcash.repository.PostRepository;
import kr.co.webcash.repository.ScrapRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
	@Autowired private PostRepository postRepository;
	@Autowired private PostMetadataRepository postMetadataRepository;
	@Autowired private PostRevisionService postRevisionService;
	@Autowired private ScrapRepository scrapRepository;
	@Autowired private CategoryRepository categoryRepository;
	@Autowired private PostTagService postTagService;
	
	@Override
	public void save(Post post) {
		Post lastPost = postRepository.findLast();
		if(lastPost == null){
			post.setId(1);
		}else{
			post.setId(lastPost.getId() + 1);
		}

		postRepository.insert(post);
		postRevisionService.save(post);
		
		insertTag(post);
		insertPostMetadata(post);
		insertScrap(post);
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
	
	private void insertTag(Post post){
		List<PostTag> postTagList = post.getPostTagList();
		
		if(postTagList == null || postTagList.isEmpty())	return;
		
		for(PostTag postTag : postTagList){
			postTag.setPost(post);
			postTagService.save(postTag);
		}
	}
	
	private void insertPostMetadata(Post post){
		PostMetadata metadata = post.getPostMetadata();
		metadata.setPost(post);
		
		postMetadataRepository.insert(metadata);
	}
	
	private void insertScrap(Post post) {
		List<Scrap> scrapList = Scrap.convert(post.getContents());
		
		for(Scrap scrap : scrapList){
			Post targetPost = findByBlogIdAndDisplayId(scrap.getScrappedBlog().getId(), scrap.getScrappedPostDisplayId());
			if(targetPost == null)	continue;
			
			if(targetPost.getPostMetadata().getCanScrap()){
				scrap.setPost(post);
				scrap.setScrappedPostContents(targetPost.getContents());
				scrap.setScrappedPostTitle(targetPost.getTitle());
				
				scrapRepository.insert(scrap);
			}
		}
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
		StringBuffer buffer = new StringBuffer(post.getContents());
		List<Scrap> scrapList = Scrap.convert(post.getContents());
		
		for(Scrap scrap : scrapList){
			Scrap findScrap = scrapRepository.findByPostIdAndTargetBlogIdAndTargetPostDisplayId(
					post.getId(), 
					scrap.getScrappedBlog().getId(), 
					scrap.getScrappedPostDisplayId());
			
			if(findScrap == null)		continue;
			
			StringBuffer changeText = new StringBuffer();
			changeText.append("<div class=\"scrap\">")
					.append(findScrap.getScrappedPostContents())
					.append("</div>");
			
			int startIndex = -1;
			while((startIndex = buffer.indexOf(findScrap.getTag())) != -1){
				buffer.replace(startIndex, startIndex + findScrap.getTag().length(), changeText.toString());
			}
		}
		
		post.setContents(buffer.toString());
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
