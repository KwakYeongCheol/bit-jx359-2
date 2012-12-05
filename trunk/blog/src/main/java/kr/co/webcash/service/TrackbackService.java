package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Trackback;
import kr.co.webcash.domain.post.Post;

public interface TrackbackService {
	public boolean sendTrackback(String url, Trackback trackback);
	
	public boolean canTrackback(String blogId, long postId);
	public boolean add(Trackback trackback);
	public List<Trackback> findAllByBlogIdAndpostId(String blogId, long postId);

	public void delete(Post post);

	
}
