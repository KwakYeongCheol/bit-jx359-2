package kr.co.webcash.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public boolean checkLoginIdAndPassword(String loginId, String password) {
		
		if("kwakyc87".equals(loginId)){
			return true;
		}
		
		return false;
	}

}
