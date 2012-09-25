package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import kr.co.webcash.domain.CommentType;
import kr.co.webcash.domain.Post;
import kr.co.webcash.domain.Scrap;

@Repository
public class PostRepositoryImpl implements PostRepository {
	
	@Autowired SqlMapClientTemplate template;
	
	@Autowired CommentRepository commentRepository;

	@Autowired private ScrapRepository scrapRepository;

	@Override
	public Post findLastPostByBlogId(String blogId) {
		Post post = (Post) template.queryForObject("Post.findLastPostByBlogId", blogId);
		addScrap(blogId, post);
		addComments(blogId, post);
		
		return post;
	}

	private void addScrap(String blogId, Post post) {
		if(post!=null){
			Scrap scrap = scrapRepository.findByBlogIdAndPostId(blogId, post.getId());
			post.setScrap(scrap);
		}
	}
	private void addScrap(String blogId, List<Post> postList) {
		for(Post post: postList){
			addScrap(blogId, post);
		}
	}
	
	

	private void addComments(String blogId, Post post) {
		if(post != null){
			post.setCommentList(commentRepository.findAllByBlogIdAndTargetIdAndType(blogId, post.getId(), CommentType.post));
		}
	}
	
	private void addComments(String blogId, List<Post> postList){
		for(Post post : postList){
			addComments(blogId, post);
		}
	}

	@Override
	public void insert(Post post) {
		template.insert("Post.insert", post);
	}

	@Override
	public List<Post> findAllByBlogId(String blogId) {
		List<Post> postList = template.queryForList("Post.findAllByBlogId", blogId);
		addComments(blogId, postList);
		addScrap(blogId, postList);
		return postList;
	}

	@Override
	public Post findByIdAndBlogId(String id, String blogId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", id);
		param.put("blogId", blogId);
		Post post = (Post) template.queryForObject("Post.findByIdAndBlogId", param );
		addScrap(blogId, post);
		addComments(blogId, post);
		return post;
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
	public List<Post> findAllByBlogIdAndCategoryId(String blogId, String categoryId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("blogId", blogId);
		param.put("categoryId", categoryId);
		List<Post> postList = template.queryForList("Post.findAllByBlogIdAndCategoryId", param );
		addComments(blogId, postList);
		addScrap(blogId, postList);
		return postList;
	}

	@Override
	public Post findLastByBlogIdAndCategoryId(String blogId, String categoryId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("blogId", blogId);
		param.put("categoryId", categoryId);
		Post post = (Post) template.queryForObject("Post.findLastByBlogIdAndCategoryId", param);
		addComments(blogId, post);
		addScrap(blogId, post);
		return post;
	}

}