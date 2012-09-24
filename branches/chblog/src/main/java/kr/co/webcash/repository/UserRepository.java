package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.User;

public interface UserRepository {
	
	public List<User> findAll();

	public void insert(User user);

	public User findByLoginId(String loginId);

}