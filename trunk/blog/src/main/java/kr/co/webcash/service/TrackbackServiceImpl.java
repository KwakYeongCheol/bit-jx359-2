package kr.co.webcash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.webcash.domain.Post;
import kr.co.webcash.domain.Trackback;
import kr.co.webcash.repository.TrackbackRepository;

@Service
public class TrackbackServiceImpl implements TrackbackService {
	@Autowired 	private PostService postService;
	@Autowired private TrackbackRepository trackbackRepository;

	@Override
	public boolean canTrackback(String blogId, String postId) {
		if(postService.findByIdAndBlogId(postId,blogId) != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean add(Trackback trackback) {
		trackbackRepository.insert(trackback);
		return true;
	}

	@Override
	public List<Trackback> findAllByBlogIdAndpostId(String blogId, String postId) {
		return trackbackRepository.findAllByBlogIdAndPostId(blogId, postId);
	}

}
