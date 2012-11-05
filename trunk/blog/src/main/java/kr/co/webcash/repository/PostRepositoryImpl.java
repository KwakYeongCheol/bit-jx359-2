package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.CommentType;
import kr.co.webcash.domain.post.Post;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImpl implements PostRepository {
	@Autowired private SqlSession sqlSession;
	@Autowired private CommentRepository commentRepository;
	@Autowired private ScrapRepository scrapRepository;
	@Autowired private TrackbackRepository trackbackRepository;
	@Autowired private PostMetadataRepository postMetadataRepository;
	@Autowired private PostRevisionRepository postRevisionRepository;
	@Autowired private PostTagRepository postTagRepository;
	

	private void addMoreInfo(List<Post> postList) {
		for(Post post : postList){
			addMoreInfo(post);
		}
	}
	
	private void addMoreInfo(Post post){
		addPostTag(post);
		addComments(post);
		addTrackback(post);
		addPostMetadata(post);
		addPostRevisionHistory(post);
	}
	
	private void addPostTag(Post post){
		if(post != null){
			post.setPostTagList(postTagRepository.findAllByPostId(post.getId()));
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
	
	private void addPostRevisionHistory(Post post) {
		if(post != null){
			post.setPostRevisionList(postRevisionRepository.findAllByPost(post));
		}
	}

	@Override
	public void insert(Post post) {
		sqlSession.insert("Post.insert", post);
	}
	
	@Override
	public void update(Post post) {
		sqlSession.update("Post.update", post);
		
	}
	
	@Override
	public void delete(Post post) {
		sqlSession.delete("Post.delete", post);
	}
	
	@Override
	public Post findLastPostByBlogId(String blogId) {
		Post post = (Post) sqlSession.selectOne("Post.findLastPostByBlogId", blogId);
		addMoreInfo(post);
		return post;
	}
	
	@Override
	public Post findByCategoryIdAndDisplayId(long categoryId, long displayId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("categoryId", categoryId);
		param.put("displayId", displayId);
		Post post = (Post) sqlSession.selectOne("Post.findByCategoryIdAndDisplayId", param );
		addMoreInfo(post);
		
		return post;
	}
	
	@Override
	public List<Post> findAll() {
		List<Post> postList = sqlSession.selectList("Post.findAll");
		
		addMoreInfo(postList);
		return postList ;
	}

	
	@Override
	public List<Post> findAllByCategoryId(long categoryId) {
		List<Post> postList = sqlSession.selectList("Post.findAllByCategoryId", categoryId);
		
		addMoreInfo(postList);
		return postList;
	}
	
	@Override
	public List<Post> findAllByBlogIdAndPostMetadataParams(String blogId, Map postMetadataParams) {
		postMetadataParams.put("blogId", blogId);
		List<Post> postList = sqlSession.selectList("Post.findAllByBlogIdAndPostMetadataParams", postMetadataParams);
		
		addMoreInfo(postList);
		return postList;
	}
	
	@Override
	public List<Post> findAllByBlogId(String blogId) {
		List<Post> postList = sqlSession.selectList("Post.findAllByBlogId", blogId);
		
		addMoreInfo(postList);
		return postList;
	}
	
	@Override
	public Post findLastByCategoryId(long categoryId) {
		Post post = (Post) sqlSession.selectOne("Post.findLastByCategoryId", categoryId);
		addMoreInfo(post);
		
		return post;
	}


	@Override
	public Post findByBlogIdAndDisplayId(String blogId, long displayId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("displayId", displayId);
		Post post = (Post) sqlSession.selectOne("Post.findByBlogIdAndDisplayId", params);
		
		addMoreInfo(post);
		return post;
	}

	@Override
	public Post findLastByBlogId(String blogId) {
		Post post = (Post) sqlSession.selectOne("Post.findLastByBlogId", blogId);
		addMoreInfo(post);
		
		return post;
	}

	@Override
	public Post findLast() {
		Post post = (Post) sqlSession.selectOne("Post.findLast");
		
		addMoreInfo(post);
		return post;
	}

	@Override
	public List<Post> findAllByQuery(String query) {
		List<Post> postList = sqlSession.selectList("Post.findAllByQuery", query);
		
		addMoreInfo(postList);
		return postList;
	}

	@Override
	public Post findById(long id) {
		Post post = (Post) sqlSession.selectOne("Post.findById", id);
		
		addMoreInfo(post);
		return post;
	}

	@Override
	public int total(String blogId) {
		int count = sqlSession.selectOne("Post.total", blogId);
		return count;
	}

	@Override
	public List<Post> select(int startRow, int endRow, String blogId, Map postMetadataParams) {
		RowBounds rowBounds = new RowBounds(startRow,endRow);
		List<Post> postList = sqlSession.selectList("Post.select",blogId, rowBounds);
		addMoreInfo(postList);
		return postList;
	}

	@Override
	public int totalByisPublic(String blogId) {
		int count = sqlSession.selectOne("Post.totalByisPublic", blogId);
		return count;
	}

	@Override
	public List<Post> selectByisPublic(int startRow, int endRow, String blogId, Map postMetadataParams) {
		RowBounds rowBounds = new RowBounds(startRow,endRow);
		List<Post> postList = sqlSession.selectList("Post.selectByisPublic",blogId, rowBounds);
		addMoreInfo(postList);
		return postList;
	}

}

