package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Trackback;

public interface TrackbackService {
	
	public boolean canTrackback(String blogId, long postId);
	public boolean add(Trackback trackback);
	public List<Trackback> findAllByBlogIdAndpostId(String blogId, long postId);
	
}
