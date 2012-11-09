package kr.co.webcash.service;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private PostRepository postRepository;
	private PostMetadataRepository postMetadataRepository;
	private PostRevisionService postRevisionService;
	private ScrapRepository scrapRepository;
	private CategoryRepository categoryRepository;
	private PostTagService postTagService;
	
	@Autowired
	public void setDependencies(PostRepository postRepository, 
			PostMetadataRepository postMetadataRepository,
			PostRevisionService postRevisionService,
			PostTagService postTagService,
			ScrapRepository scrapRepository, 
			CategoryRepository categoryRepository){
		
		this.postRepository = postRepository;
		this.postMetadataRepository = postMetadataRepository;
		this.postRevisionService = postRevisionService;
		this.postTagService = postTagService;
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
	
	@Override
	public List<Post> listByBlogIdAndCategoryDisplayId(String blogId, long categoryDisplayId) {
		Category category = categoryRepository.findByBlogIdAndDisplayId(blogId, categoryDisplayId);
		
		return postRepository.findAllByCategoryId(category.getId());
	}

	@Override
	public List<Post> listPublic(String blogId) {
		Map params = new HashMap();
		params.put("isPublic", "true");
		List<Post> postList = postRepository.findAllByBlogIdAndPostMetadataParams(blogId, params);
		
		convertFromScrapTagToScrapContents(postList);
		
		return postList;
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
			List<Scrap> scrapList = Scrap.convert(post.getContents());
			
			for(Scrap scrap : scrapList){
				Scrap findScrap = scrapRepository.findByPostIdAndTargetBlogIdAndTargetPostDisplayId(
						post.getId(), 
						scrap.getScrappedBlog().getId(), 
						scrap.getScrappedPostDisplayId());
				
				if(findScrap != null){
					StringBuffer changeText = new StringBuffer();
					changeText.append("<div class=\"scrap\">")
						.append(findScrap.getScrappedPostContents())
						.append("</div>");
					
					int startIndex = buffer.indexOf(findScrap.getTag());
					buffer.replace(startIndex, startIndex + findScrap.getTag().length(), changeText.toString());
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
	public Post findById(long id) {
		return postRepository.findById(id);
	}

	@Override
	public Post findByBlogIdAndDisplayId(String blogId, long displayId) {
		return postRepository.findByBlogIdAndDisplayId(blogId, displayId);
	}

	@Override
	public List<Post> search(String query) {
		return postRepository.findAllByQuery(query);
	}

	@Override
	public Page getPage(String blogId, int pageNum) {
		Page page = new Page();
		
		page.setCurrentPage(pageNum);
		page.setCount(postRepository.total(blogId));
		page.setStartPage((page.getCurrentPage() - 1) * page.getPageSize() + 1);
		page.setEndPage(page.getCurrentPage() * page.getPageSize());
		page.setPageGroupCount(page.getCount()/(page.getPageSize() * page.getPageGroupSize()) + (page.getCount()%(page.getPageSize() * page.getPageGroupSize()) == 0 ? 0 : 1));
		page.setNumPageGroup((int)Math.ceil((double)page.getCurrentPage()/page.getPageGroupSize()));
		
		return page;
	}

	@Override
	public List<Post> listAllByPage(Page page, String blogId) {
		ArrayList<Post> postList;
		Map params = new HashMap();
		params.put("isPublic", "true");
		
		if(page.getCount() > 0 ){
			postList= (ArrayList) postRepository.select(page.getStartPage()-1,page.getPageSize(),blogId,params);	
		}else{
			postList = new ArrayList<Post>();
		}
		
		convertFromScrapTagToScrapContents(postList);
		
		return postList;
	}

	@Override
	public Page getPagePublic(String blogId, int pageNum) {
		Page page = new Page();
		
		page.setCurrentPage(pageNum);
		page.setCount(postRepository.totalByisPublic(blogId));
		page.setStartPage((page.getCurrentPage() - 1) * page.getPageSize() + 1);
		page.setEndPage(page.getCurrentPage() * page.getPageSize());
		page.setPageGroupCount(page.getCount()/(page.getPageSize() * page.getPageGroupSize()) + (page.getCount()%(page.getPageSize() * page.getPageGroupSize()) == 0 ? 0 : 1));
		page.setNumPageGroup((int)Math.ceil((double)page.getCurrentPage()/page.getPageGroupSize()));
		
		return page;
	}

	@Override
	public List<Post> listPublicByPage(Page page, String blogId) {
		ArrayList<Post> postList;
		Map params = new HashMap();
		params.put("isPublic", "true");
		
		if(page.getCount() > 0 ){
			postList= (ArrayList) postRepository.selectByisPublic(page.getStartPage()-1,page.getPageSize(),blogId, params);	
		}else{
			postList = new ArrayList<Post>();
		}
		
		return postList;
	}
}
