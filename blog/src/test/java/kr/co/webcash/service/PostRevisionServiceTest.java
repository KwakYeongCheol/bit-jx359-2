package kr.co.webcash.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.PostRevision;
import kr.co.webcash.repository.PostRevisionRepository;
import kr.co.webcash.service.post.PostRevisionService;
import kr.co.webcash.service.post.PostRevisionServiceImpl;
import kr.co.webcash.service.post.PostService;
import kr.co.webcash.utils.PostRevisionUtils;

import org.junit.Before;
import org.junit.Test;

public class PostRevisionServiceTest {
	
	private PostRevisionService postRevisionService;
	private PostService postService;
	
	@Before
	public void before(){
		postRevisionService = new PostRevisionServiceImpl();
		postService = new MockPostService();
		postRevisionService.init(postService, new MockPostRevisionRepository());
	}
	
	@Test
	public void insertAndPatch(){
		String firstPostContents = "Gwak\nChul<br />";
		String secondPostContents = "Kwak\n<p style=\"width:500px;\">Who</p>\nhi!";
		String thirdPostContents = "Kwak\nWhos\nChul\nhi";
		
		Post firstPost = new Post();
		firstPost.setId(1);
		firstPost.setContents(firstPostContents);
		
		save(firstPost);
		
		assertThat(postService.findById(firstPost.getId()).getContents(), is(firstPostContents));
		PostRevision postRevision = postRevisionService.currentRevision(firstPost);
		assertThat(postRevisionService.list(firstPost).size(), is(1));
		assertThat(postRevision.getDisplayId(), is(Long.valueOf(1)));
		
		Post secondPost = new Post();
		secondPost.setId(1);
		secondPost.setContents(secondPostContents);
		save(secondPost);
		
		assertThat(postService.findById(secondPost.getId()).getContents(), is(secondPostContents));
		postRevision = postRevisionService.currentRevision(secondPost);
		assertThat(postRevisionService.list(secondPost).size(), is(2));
		assertThat(postRevision.getDisplayId(), is(Long.valueOf(2)));
		
		Post thirdPost = new Post();
		thirdPost.setId(1);
		thirdPost.setContents(thirdPostContents);
		save(thirdPost);
		
		assertThat(postService.findById(thirdPost.getId()).getContents(), is(thirdPostContents));
		postRevision = postRevisionService.currentRevision(thirdPost);
		List<PostRevision> postRevisionList = postRevisionService.list(thirdPost);
		assertThat(postRevisionList.size(), is(3));
		assertThat(postRevision.getDisplayId(), is(Long.valueOf(3)));
		
		Post findPost = postService.findById(thirdPost.getId());
		findPost.setPostRevisionList(postRevisionService.list(findPost));
		assertThat(findPost.getContents(1), is(firstPostContents));
		assertThat(findPost.getContents(2), is(secondPostContents));
		assertThat(findPost.getContents(3), is(thirdPostContents));
		
		System.out.println(PostRevisionUtils.generateDiffRows(thirdPostContents, secondPostContents));
	}
	
	private void save(Post post){
		if(this.postService.findById(post.getId()) == null){
			this.postRevisionService.save(post);
			this.postService.save(post); // insert
		}else{
			this.postRevisionService.save(post);
			this.postService.update(post); // update
		}
	}
	
}



class MockPostRevisionRepository implements PostRevisionRepository{
	private Map<Long, List<PostRevision>> repository = new HashMap<Long, List<PostRevision>>();

	@Override
	public PostRevision findLastRevisionByPostId(long postId) {
		List<PostRevision> revisionList = repository.get(postId);
		if(revisionList == null)		return null;
		
		
		Collections.sort(revisionList, new Comparator<PostRevision>() {
			@Override
			public int compare(PostRevision o1, PostRevision o2) {
				if(o1.getDisplayId() < o2.getDisplayId()){
					return 1;
				}else if(o1.getDisplayId() == o2.getDisplayId()){
					return 0;
				}else{
					return -1;
				}
			}
		});
		
		return revisionList.get(0);
	}

	@Override
	public boolean insert(PostRevision postRevision) {
		List<PostRevision> revisionList = repository.get(postRevision.getPost().getId());
		if(revisionList == null){
			revisionList = new ArrayList<PostRevision>();
		}
		
		revisionList.add(postRevision);
		
		repository.put(postRevision.getPost().getId(), revisionList);
		return true;
	}

	@Override
	public boolean update(PostRevision updatedPostRevision) {
		List<PostRevision> revisionList = repository.get(updatedPostRevision.getPost().getId());
		
		for(PostRevision revision : revisionList){
			if(updatedPostRevision.getDisplayId() == revision.getDisplayId()){
				revision.setDiff(updatedPostRevision.getDiff());
			}
		}
		
		return true;
	}

	@Override
	public List<PostRevision> findAllByPostAndFromRevisionAndToRevision(Post post, long fromDisplayId, long toDisplayId) {
		
		List<PostRevision> revisionList = repository.get(post.getId());
		Collections.sort(revisionList, new Comparator<PostRevision>() {
			@Override
			public int compare(PostRevision o1, PostRevision o2) {
				if(o1.getDisplayId() < o2.getDisplayId()){
					return 1;
				}else if(o1.getDisplayId() == o2.getDisplayId()){
					return 0;
				}else{
					return -1;
				}
			}
		});
		
		List<PostRevision> resultRevisionList = new ArrayList<PostRevision>();
		
		for(PostRevision revision : revisionList){
			if(revision.getDisplayId() < fromDisplayId || revision.getDisplayId() > toDisplayId)	continue;
			
			resultRevisionList.add(revision);
		}
		
		return resultRevisionList;
	}

	@Override
	public List<PostRevision> findAllByPost(Post post) {
		return repository.get(post.getId());
	}

	@Override
	public PostRevision findByPostIdAndDisplayId(long postId, long displayId) {
		// TODO Auto-generated method stub
		return null;
	}
}

class MockPostService implements PostService{
	
	private Map<Long, Post> repository = new HashMap<Long, Post>();

	@Override
	public void save(Post post) {
		repository.put(post.getId(), post);
	}

	@Override
	public void update(Post post) {
		repository.put(post.getId(), post);
	}
	
	@Override
	public Post findById(long id) {
		return repository.get(id);
	}


	@Override
	public void delete(Post post) {
	}

	@Override
	public long findLastDisplayIdByBlogId(String blogId) {
		return 0;
	}

	@Override
	public Post findByBlogIdAndDisplayId(String blogId, long displayId) {
		return null;
	}

	@Override
	public List<Post> listByBlogIdAndCategoryDisplayId(String blogId,
			long categoryDisplayId) {
		return null;
	}

	@Override
	public List<Post> search(String query) {
		return null;
	}

	@Override
	public Page getPage(String blogId, int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page getPagePublic(String blogId, int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> listPublicByBlogIdAndPageNumber(String blogId,
			int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> listPublicByBlogIdAndPage(String blogId, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> listByBlogId(String blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> listByBlogIdAndPageNumber(String blogId, int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> listByBlogIdAndPage(String blogId, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}





