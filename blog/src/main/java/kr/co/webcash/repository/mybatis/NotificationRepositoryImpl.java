package kr.co.webcash.repository.mybatis;

import java.util.List;

import kr.co.webcash.domain.notification.Notification;
import kr.co.webcash.repository.NotificationRepository;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {
	@Autowired private SqlSession session;

	@Override
	public void insert(Notification notification) {
		session.insert("Notification.insert", notification);
	}
	
	@Override
	public Notification findLast() {
		return session.selectOne("Notification.findLast");
	}

	@Override
	public List<Notification> findAllByBlogId(String blogId) {
		return session.selectList("Notification.findAllByBlogId", blogId);
	}

	@Override
	public List<Notification> findAllByBlogIdAndPage(String blogId, int pageNumber, int pageSize) {
		RowBounds rowBounds = new RowBounds((pageNumber-1) * pageSize, pageSize);
		return session.selectList("Notification.findAllByBlogId", blogId, rowBounds);
	}
}
