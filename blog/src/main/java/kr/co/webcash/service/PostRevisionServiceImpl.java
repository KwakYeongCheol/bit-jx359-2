package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.PostRevision;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.repository.PostRevisionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostRevisionServiceImpl implements PostRevisionService{
	@Autowired private PostRevisionRepository postRevisionRepository;
	@Autowired private PostService postService;
	
	@Override
	public void init(PostService postService, PostRevisionRepository postRevisionRepository) {
		this.postService = postService;
		this.postRevisionRepository = postRevisionRepository;
	}

	@Override
	public boolean save(Post post) {
		PostRevision lastRevision = postRevisionRepository.findLastRevisionByPostId(post.getId());
		
		if(lastRevision == null){
			return postRevisionRepository.insert(
				new PostRevision(post, Long.valueOf(1), "")
			);
		}else{
			Post lastPost = postService.findById(post.getId());
			
			PostRevision postRevision = new PostRevision(
					post, 
					lastRevision.getDisplayId(), 
					PostRevision.diff(post.getContents(), lastPost.getContents()));
			
			postRevisionRepository.update(
				postRevision
			);
			
			postRevisionRepository.insert(
				new PostRevision(post, lastRevision.getDisplayId() + 1, "")	
			);
			
			return true;
		}
	}

	@Override
	public PostRevision currentRevision(Post post) {
		return postRevisionRepository.findLastRevisionByPostId(post.getId());
	}

	@Override
	public List<PostRevision> list(Post post) {
		return postRevisionRepository.findAllByPost(post);
	}

	@Override
	public PostRevision get(long postId, long postRevisionDisplayId) {
		return postRevisionRepository.findByPostIdAndDisplayId(postId, postRevisionDisplayId);
	}
}
