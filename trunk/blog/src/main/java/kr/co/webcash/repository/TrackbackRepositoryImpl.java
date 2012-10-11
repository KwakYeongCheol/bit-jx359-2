package kr.co.webcash.repository;

import java.util.List;

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
	public List<Trackback> findAllByPostId(long postId) {
		return template.queryForList("Trackback.findAllByPostId",postId);
	}

}
