package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Trackback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrackbackRepositoryImpl implements TrackbackRepository {
	@Autowired private SqlMapClientTemplate template;
	
	@Override
	public void insert(Trackback trackback) {
		template.insert("Trackback.insert", trackback);
	}
	
	@Override
	public List<Trackback> findAllByBlogIdAndPostId(String blogId, long postId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("postId", postId);
		return template.queryForList("Trackback.findAllByBlogIdAndPostId",params);
	}

}
