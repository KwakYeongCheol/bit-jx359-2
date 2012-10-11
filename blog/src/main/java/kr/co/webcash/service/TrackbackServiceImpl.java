package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Trackback;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.repository.TrackbackRepository;
import kr.co.webcash.utils.TrackbackUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackbackServiceImpl implements TrackbackService {
	@Autowired private PostService postService;
	@Autowired private TrackbackRepository trackbackRepository;

	@Override
	public boolean sendTrackback(String url, Trackback trackback) {
		return TrackbackUtils.trackback(url, trackback);
	}
	
	@Override
	public boolean canTrackback(String blogId, long postId) {
		if(postService.findByBlogIdAndDisplayId(blogId, postId) != null){
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
	public List<Trackback> findAllByBlogIdAndpostId(String blogId, long postId) {
		Post post = postService.findByBlogIdAndDisplayId(blogId, postId);
		
		return trackbackRepository.findAllByPostId(post.getId());
	}


}
