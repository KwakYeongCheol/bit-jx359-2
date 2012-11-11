package kr.co.webcash.service;

import kr.co.webcash.domain.user.User;

public interface UserService {

	boolean checkLoginIdAndPassword(String loginId, String password);

	boolean save(User user);

	User findByLoginId(String loginId);

	boolean update(User user);

}
