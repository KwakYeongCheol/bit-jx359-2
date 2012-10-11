package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.CommentType;
import kr.co.webcash.domain.Scrap;
import kr.co.webcash.domain.post.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImpl implements PostRepository {
	@Autowired private SqlMapClientTemplate template;
	@Autowired private CommentRepository commentRepository;
	@Autowired private ScrapRepository scrapRepository;
	@Autowired private TrackbackRepository trackbackRepository;
	@Autowired private PostMetadataRepository postMetadataRepository;
	

	private void addMoreInfo(List<Post> postList) {
		for(Post post : postList){
			addMoreInfo(post);
		}
	}
	
	private void addMoreInfo(Post post){
		addScrap(post);
		addComments(post);
		addTrackback(post);
		addPostMetadata(post);
	}
	
	private void addScrap(Post post) {
		if(post!=null){
			Scrap scrap = scrapRepository.findByPostId(post.getId());
			post.setScrap(scrap);
		}
	}

	private void addComments(Post post) {
		if(post != null){
			post.setCommentList(commentRepository.findAllByTargetIdAndType(post.getId(), CommentType.post));
		}
	}
	
	private void addTrackback(Post post) {
		if(post != null){
			post.setTrackbackList(trackbackRepository.findAllByPostId(post.getId()));
		}
	}
	
	private void addPostMetadata(Post post) {
		if(post != null){
			post.setPostMetadata(postMetadataRepository.findByPostId(post.getId()));
		}
	}


	@Override
	public void insert(Post post) {
		template.insert("Post.insert", post);
	}
	
	@Override
	public void update(Post post) {
		template.update("Post.update", post);
		
	}
	
	@Override
	public void delete(Post post) {
		template.delete("Post.delete", post);
	}
	
	@Override
	public Post findLastPostByBlogId(String blogId) {
		Post post = (Post) template.queryForObject("Post.findLastPostByBlogId", blogId);
		addMoreInfo(post);
		return post;
	}
	
	@Override
	public Post findByCategoryIdAndDisplayId(long categoryId, long displayId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("categoryId", categoryId);
		param.put("displayId", displayId);
		Post post = (Post) template.queryForObject("Post.findByCategoryIdAndDisplayId", param );
		addMoreInfo(post);
		
		return post;
	}
	
	@Override
	public List<Post> findAllByCategoryId(long categoryId) {
		List<Post> postList = template.queryForList("Post.findAllByCategoryId", categoryId);
		
		addMoreInfo(postList);
		return postList;
	}
	
	

	
	@Override
	public List<Post> findAllByBlogIdAndPostMetadataParams(String blogId, Map postMetadataParams) {
		postMetadataParams.put("blogId", blogId);
		List<Post> postList = template.queryForList("Post.findAllByBlogIdAndPostMetadataParams", postMetadataParams);
		
		addMoreInfo(postList);
		return postList;
	}
	
	@Override
	public List<Post> findAllByBlogId(String blogId) {
		List<Post> postList = template.queryForList("Post.findAllByBlogId", blogId);
		
		addMoreInfo(postList);
		return postList;
	}
	
	@Override
	public Post findLastByCategoryId(long categoryId) {
		Post post = (Post) template.queryForObject("Post.findLastByCategoryId", categoryId);
		addMoreInfo(post);
		
		return post;
	}


	@Override
	public Post findByBlogIdAndDisplayId(String blogId, long displayId) {
		Map params = new HashMap();
		params.put("blogId", blogId);
		params.put("displayId", displayId);
		Post post = (Post) template.queryForObject("Post.findByBlogIdAndDisplayId", params);
		
		addMoreInfo(post);
		return post;
	}

	@Override
	public Post findLastByBlogId(String blogId) {
		Post post = (Post) template.queryForObject("Post.findLastByBlogId", blogId);
		addMoreInfo(post);
		
		return post;
	}

	@Override
	public Post findLast() {
		Post post = (Post) template.queryForObject("Post.findLast");
		
		addMoreInfo(post);
		return post;
	}


}

