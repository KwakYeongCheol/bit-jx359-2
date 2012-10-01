package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
@Autowired SqlMapClientTemplate template;
	
	public List<User> findAll() {
		return template.queryForList("User.findAll");
	}

	@Override
	public void insert(User user) {
		template.insert("User.insert", user);
	}

	@Override
	public User findByLoginId(String loginId) {
		return (User) template.queryForObject("User.findById", loginId);
	}

	@Override
	public void update(User user) {
		template.update("User.update", user);
	}

	@Override
	public User findByLoginIdAndPassword(String loginId, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("loginId", loginId);
		params.put("password", password);
		return (User) template.queryForObject("User.findByIdAndPassword", params);
	}

}
