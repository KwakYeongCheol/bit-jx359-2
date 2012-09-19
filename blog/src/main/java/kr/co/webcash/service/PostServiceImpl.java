package kr.co.webcash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.webcash.domain.Post;
import kr.co.webcash.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public int findLastIdByBlogId(String blogId) {
		Post post = postRepository.findLastPostByBlogId(blogId);
		
		if(post==null){
			return 0;
		}

		return Integer.parseInt(post.getId());
	}

	@Override
	public void save(Post post) {
		postRepository.insert(post);

	}

	@Override
	public List<Post> listByBlogId(String blogId) {
		return postRepository.findAllByBlogId(blogId);
	}

	@Override
	public Post findByIdAndBlogId(String id, String blogId) {
	    return postRepository.findByIdAndBlogId(id, blogId);
		
	}

	@Override
	public void update(Post post) {
		postRepository.update(post);
	}

}
