package kr.co.webcash.repository.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.comment.CommentType;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.repository.CommentRepository;
import kr.co.webcash.repository.PostMetadataRepository;
import kr.co.webcash.repository.PostRepository;
import kr.co.webcash.repository.PostRevisionRepository;
import kr.co.webcash.repository.PostTagRepository;
import kr.co.webcash.repository.ScrapRepository;
import kr.co.webcash.repository.TrackbackRepository;

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
	
	private List<Post> wrap(List<Post> postList){
		for(Post post : postList){
			wrap(post);
		}
		
		return postList;
	}
	
	private Post wrap(Post post){
		if(post != null){
			addPostTag(post);
			addComments(post);
			addTrackback(post);
			addPostMetadata(post);
			addPostRevisionHistory(post);
		}
		
		return post;
	}
	
	private void addPostTag(Post post){
		post.setPostTagList(postTagRepository.findAllByPostId(post.getId()));
	}
	
	private void addComments(Post post) {
		post.setCommentList(commentRepository.findAllByTargetIdAndType(post.getId(), CommentType.post));
	}
	
	private void addTrackback(Post post) {
		post.setTrackbackList(trackbackRepository.findAllByPostId(post.getId()));
	}
	
	private void addPostMetadata(Post post) {
		post.setPostMetadata(postMetadataRepository.findByPostId(post.getId()));
	}
	
	private void addPostRevisionHistory(Post post) {
		post.setPostRevisionList(postRevisionRepository.findAllByPost(post));
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
		return wrap(sqlSession.<Post>selectOne("Post.findLastPostByBlogId", blogId));
	}
	
	@Override
	public Post findByCategoryIdAndDisplayId(long categoryId, long displayId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("categoryId", categoryId);
		param.put("displayId", displayId);
		return wrap(sqlSession.<Post>selectOne("Post.findByCategoryIdAndDisplayId", param ));
	}
	
	@Override
	public List<Post> findAll() {
		return wrap(sqlSession.<Post>selectList("Post.findAll"));
	}

	@Override
	public List<Post> findAllByBlogId(String blogId) {
		return wrap(sqlSession.<Post>selectList("Post.findAllByBlogId", blogId));
	}
	
	@Override
	public List<Post> findAllByCategoryId(long categoryId) {
		return wrap(sqlSession.<Post>selectList("Post.findAllByCategoryId", categoryId));
	}
	

	@Override
	public List<Post> findAllByCategoryIdAndOffsetAndLimit(long categoryId, int offset, int limit) {
		return wrap(sqlSession.<Post>selectList("Post.findAllByCategoryId", categoryId, new RowBounds(offset, limit)));
	}
	
	
	@Override
	public Post findLastByCategoryId(long categoryId) {
		return wrap(sqlSession.<Post>selectOne("Post.findLastByCategoryId", categoryId));
	}


	@Override
	public Post findByBlogIdAndDisplayId(String blogId, long displayId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("displayId", displayId);
		return wrap(sqlSession.<Post>selectOne("Post.findByBlogIdAndDisplayId", params));
	}

	@Override
	public Post findLastByBlogId(String blogId) {
		return wrap(sqlSession.<Post>selectOne("Post.findLastByBlogId", blogId));
	}

	@Override
	public Post findLast() {
		return wrap(sqlSession.<Post>selectOne("Post.findLast"));
	}

	@Override
	public List<Post> findAllByQuery(String query) {
		return wrap(sqlSession.<Post>selectList("Post.findAllByQuery", query));
	}

	@Override
	public Post findById(long id) {
		return wrap(sqlSession.<Post>selectOne("Post.findById", id));
	}

	@Override
	public int countByBlogId(String blogId) {
		return sqlSession.<Integer>selectOne("Post.countByBlogId", blogId);
	}

	@Override
	public int countPublicByBlogId(String blogId) {
		return sqlSession.<Integer>selectOne("Post.countPublicByBlogId", blogId);
	}

	@Override
	public List<Post> findAllByBlogIdAndPage(String blogId, int offset, int limit) {
		RowBounds rowBounds = new RowBounds(offset, limit);
		return wrap(sqlSession.<Post>selectList("Post.findAllByBlogId", blogId, rowBounds));
	}

	@Override
	public List<Post> findAllPublicByBlogIdAndPage(String blogId, int offset, int limit) {
		RowBounds rowBounds = new RowBounds(offset, limit);
		return wrap(sqlSession.<Post>selectList("Post.findAllPublicByBlogId", blogId, rowBounds));
	}

	@Override
	public List<Post> findAllTempByBlogId(String blogId) {
		return sqlSession.<Post>selectList("Post.findAllTempByBlogId", blogId);
	}

	@Override
	public int countByCategoryId(long categoryId) {
		return sqlSession.<Integer>selectOne("Post.countByCategoryId", categoryId);
	}
}
