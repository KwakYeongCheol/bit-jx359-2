package kr.co.webcash.service;

import kr.co.webcash.domain.Trackback;

public interface TrackbackService {
	
	public boolean canTrackback(String blogId, String postId);
	public boolean add(Trackback trackback);

}
