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

}
