package gmc.project.blockchain.services.implementations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gmc.project.blockchain.daos.UserDao;
import gmc.project.blockchain.entities.UserEntity;
import gmc.project.blockchain.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity foundUser = findByUserName(username);
		return new User(username, foundUser.getPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserEntity findByUserName(String userName) {
		UserEntity foundUser = null;		
		if(userName.contains("@")) {
			foundUser = userDao.findByEmail(userName).orElse(null);
		} else {
			foundUser = userDao.findByMobileNumber(userName).orElse(null);
		}		
		if(foundUser == null)
			throw new UsernameNotFoundException(userName);		
		return foundUser;
	}

}
