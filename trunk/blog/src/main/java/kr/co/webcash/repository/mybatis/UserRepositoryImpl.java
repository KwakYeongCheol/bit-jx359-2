package kr.co.webcash.repository.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.User;
import kr.co.webcash.repository.UserRepository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	@Autowired private SqlSession sqlSession;
	
	@Override
	public void insert(User user) {
		sqlSession.insert("User.insert", user);
	}
	
	@Override
	public void update(User user) {
		sqlSession.update("User.update", user);
	}
	
	public List<User> findAll() {
		return sqlSession.selectList("User.findAll");
	}

	@Override
	public User findByLoginId(String loginId) {
		return (User) sqlSession.selectOne("User.findById", loginId);
	}

	@Override
	public User findByLoginIdAndPassword(String loginId, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("loginId", loginId);
		params.put("password", password);
		return (User) sqlSession.selectOne("User.findByIdAndPassword", params);
	}
}
