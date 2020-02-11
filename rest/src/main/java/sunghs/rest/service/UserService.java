package sunghs.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sunghs.rest.entity.UserInfo;
import sunghs.rest.repository.UserRepository;

public @Service class UserService {

	private @Autowired UserRepository userRepository;
	
	public Optional<UserInfo> findById(final long id) {
		return userRepository.findById(id);
	}
	
	public List<UserInfo> findAll() {
		return userRepository.findAll();
	}
	
	public UserInfo save(final UserInfo userInfo) {
		return userRepository.save(userInfo);
	}
	
	public UserInfo update(final long id, final UserInfo userInfo) {
		Optional<UserInfo> currentUserInfo = this.findById(id);
		if(currentUserInfo.isPresent()) {
			UserInfo newUser = currentUserInfo.get();
			newUser.setId(id);
			newUser.setUserId(userInfo.getUserId());
			newUser.setUserName(userInfo.getUserName());
			newUser.setUserPassword(userInfo.getUserPassword());
			newUser.setUserPhone(userInfo.getUserPhone());
			newUser.setUserExt(userInfo.getUserExt());
			return this.save(newUser);
		}
		return null;
	}
	
	public boolean delete(final long id) {
		Optional<UserInfo> currentUserInfo = this.findById(id);
		if(currentUserInfo.isPresent()) {
			userRepository.deleteById(id);
			return true;
		}
		else return false;
	}
}
