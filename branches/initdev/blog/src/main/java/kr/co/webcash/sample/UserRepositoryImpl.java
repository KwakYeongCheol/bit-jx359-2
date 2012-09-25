package kr.co.webcash.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@Autowired SqlMapClientTemplate template;
	
	public List<User> findAll() {
		return template.queryForList("Sample.findAll");
	}

}
