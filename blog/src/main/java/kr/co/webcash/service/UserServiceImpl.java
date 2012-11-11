package kr.co.webcash.service;

import kr.co.webcash.domain.user.User;
import kr.co.webcash.repository.UserRepository;
import kr.co.webcash.utils.EncryptUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired private UserRepository userRepository;

	@Override
	public boolean checkLoginIdAndPassword(String loginId, String password) {
		String encryptedPassword = EncryptUtils.encrypt(password);
		
		User user = userRepository.findByLoginIdAndPassword(loginId, encryptedPassword);
		
		if(user != null){
			return true;
		}
		
		return false;
	}

	@Override
	public boolean save(User user) {
		try{
			user.setPassword(EncryptUtils.encrypt(user.getPassword()));

			userRepository.insert(user);
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public boolean update(User user) {
		try{
			user.setPassword(EncryptUtils.encrypt(user.getPassword()));
			
			userRepository.update(user);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	

	@Override
	public User findByLoginId(String loginId) {
		return (User)userRepository.findByLoginId(loginId);
	}
}
