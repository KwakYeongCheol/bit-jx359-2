package kr.co.webcash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Scrap;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.PostMetadata;
import kr.co.webcash.repository.CategoryRepository;
import kr.co.webcash.repository.PostMetadataRepository;
import kr.co.webcash.repository.PostRepository;
import kr.co.webcash.repository.ScrapRepository;
import kr.co.webcash.utils.PostUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private PostMetadataRepository postMetadataRepository;
	private ScrapRepository scrapRepository;
	private CategoryRepository categoryRepository;
	
	@Autowired
	public void setDependencies(PostRepository postRepository, 
			PostMetadataRepository postMetadataRepository,
			ScrapRepository scrapRepository, 
			CategoryRepository categoryRepository){
		
		this.postRepository = postRepository;
		this.postMetadataRepository = postMetadataRepository;
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
		
		insertPostMetadata(post);
		
		insertScrap(post);
	}

	@Override
	public void update(Post post) {
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
	
	private void insertScrap(Post post) {
		List<Map<String, String>> scrapURLList = PostUtils.parseToMap(post.getContents());
		
		for(Map<String, String> blogIdAndPostIdPair : scrapURLList){
			String blogId = blogIdAndPostIdPair.get("blogId");
			String postDisplayId = blogIdAndPostIdPair.get("postDisplayId");
			
			if(!StringUtils.hasLength(blogId) || !StringUtils.hasLength(postDisplayId))
				continue;
			
			Post targetPost = findByBlogIdAndDisplayId(blogId, Long.valueOf(postDisplayId));
			if(targetPost == null)		continue;
			
			if(targetPost.getPostMetadata().getCanScrap()){
				Scrap scrap = new Scrap();
				scrap.setPostId(post.getId());
				scrap.setScrappedBlog(new Blog(blogId));
				scrap.setScrappedPostId(targetPost.getDisplayId());
				scrap.setScrappedPostContents(targetPost.getContents());
				scrap.setScrappedPostTitle(targetPost.getTitle());
				
				scrapRepository.insert(scrap);
			}
		}
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
		List<Post> postList = postRepository.findAllByBlogId(blogId);
		
		convertFromScrapTagToScrapContents(postList);
		
		return postList;
	}
	
	private void convertFromScrapTagToScrapContents(List<Post> postList) {
		for(Post post : postList){
			StringBuffer buffer = new StringBuffer(post.getContents());
			
			List<String> scrapURLList = PostUtils.getScrapURLFromContents(post.getContents());
			
			for(String scrapURL : scrapURLList){
				Map<String, String> pair = PostUtils.parseToBlogIdAndPostIdPair(scrapURL);
				
				String blogId = pair.get("blogId");
				String postDisplayId = pair.get("postDisplayId");
				
				if(!StringUtils.hasLength(blogId) || !StringUtils.hasLength(postDisplayId))
					continue;
				
				Scrap scrap = scrapRepository.findByPostIdAndTargetBlogIdAndTargetPostId(post.getId(), blogId, postDisplayId);
				
				if(scrap != null){
					StringBuffer changeText = new StringBuffer();
					changeText.append("<div class=\"scrap\">")
						.append(scrap.getScrappedPostContents())
						.append("</div>");
					
					int startIndex = buffer.indexOf(scrapURL);
					
					buffer.replace(startIndex, startIndex + scrapURL.length(), changeText.toString());
				}
			}
			
			post.setContents(buffer.toString());
		}
	}

	@Override
	public long findLastDisplayIdByBlogId(String blogId){
		Post post = postRepository.findLastByBlogId(blogId);
		
		if(post == null)	return 0;
		else				return post.getDisplayId();
	}


	@Override
	public Post findByBlogIdAndDisplayId(String blogId, long displayId) {
		return postRepository.findByBlogIdAndDisplayId(blogId, displayId);
	}

	@Override
	public List<Post> search(String query) {
		return postRepository.findAllByQuery(query);
	}

}
