package kr.co.webcash.service;

import kr.co.webcash.domain.User;
import kr.co.webcash.repository.UserRepository;
import kr.co.webcash.utils.EncryptUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired private UserRepository userRepository;

	@Override
	public boolean checkLoginIdAndPassword(String loginId, String password) {
		User user = userRepository.findByLoginId(loginId);
		
		if(user != null){
			String decryptedPassword = EncryptUtils.descrypt(user.getPassword(), password);
			
			if(decryptedPassword!=null && decryptedPassword.equals(loginId)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean save(User user) {
		try{
			String encryptedPassword = EncryptUtils.encrypt(user.getLoginId(), user.getPassword());
			user.setPassword(encryptedPassword);

			userRepository.insert(user);
			
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
