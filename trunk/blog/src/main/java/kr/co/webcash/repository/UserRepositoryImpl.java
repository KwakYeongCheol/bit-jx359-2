package kr.co.webcash.repository;

import java.util.List;

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
		// TODO Auto-generated method stub
		template.insert("User.insert", user);
	}

	@Override
	public User findByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return (User) template.queryForObject("User.findById", loginId);
	}
	
}
