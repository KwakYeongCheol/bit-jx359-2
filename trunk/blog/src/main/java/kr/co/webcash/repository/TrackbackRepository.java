package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Trackback;

public interface TrackbackRepository {

	void insert(Trackback trackback);

	List<Trackback> findAllByBlogIdAndPostId(String blogId, long postId);

	
}
