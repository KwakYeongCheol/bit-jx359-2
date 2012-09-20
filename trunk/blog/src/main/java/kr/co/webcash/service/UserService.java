package kr.co.webcash.service;

import kr.co.webcash.domain.User;

public interface UserService {

	boolean checkLoginIdAndPassword(String loginId, String password);

	boolean save(User user);

}
