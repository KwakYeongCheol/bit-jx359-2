package kr.co.webcash.service;

import kr.co.webcash.domain.User;
import kr.co.webcash.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired private UserRepository userRepository;

	@Override
	public boolean checkLoginIdAndPassword(String loginId, String password) {
		User user = userRepository.findByLoginId(loginId);
		
		if(user != null){
			if(password.equals(user.getPassword()))		return true;
		}
		
		return false;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userRepository.insert(user);
	}

}
