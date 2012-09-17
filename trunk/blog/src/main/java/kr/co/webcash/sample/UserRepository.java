package kr.co.webcash.sample;

import java.util.List;

public interface UserRepository {
	
	public List<User> findAll();

	public void insert(String string);

}
