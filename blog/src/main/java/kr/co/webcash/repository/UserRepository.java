package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.user.User;

public interface UserRepository {
	
	public List<User> findAll();

	public void insert(User user);

	public User findByLoginId(String loginId);

	public void update(User user);

	public User findByLoginIdAndPassword(String loginId, String password);

}
