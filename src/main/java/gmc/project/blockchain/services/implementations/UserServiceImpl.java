package gmc.project.blockchain.services.implementations;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gmc.project.blockchain.daos.UserDao;
import gmc.project.blockchain.entities.UserEntity;
import gmc.project.blockchain.models.UserModel;
import gmc.project.blockchain.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(UserModel userData) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		if(userData.getId() == null) {
			UserEntity detachedUser = modelMapper.map(userData, UserEntity.class);
			detachedUser.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
			userDao.save(detachedUser);
		} else {
			UserEntity detachedUser = userDao.findById(userData.getId()).orElse(null);
			if(detachedUser == null)
				throw new UsernameNotFoundException(userData.getId());
			detachedUser.setFirstName(userData.getFirstName());
			detachedUser.setLastName(userData.getLastName());
			detachedUser.setUserName(userData.getUserName());
			detachedUser.setEmail(userData.getEmail());
			detachedUser.setMobileNumber(userData.getMobileNumber());
			userDao.save(detachedUser);
		}
		
	}

	@Override
	public UserModel findAUser(String userId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity foundUser = userDao.findById(userId).orElse(null);
		if(foundUser == null)
			throw new UsernameNotFoundException(userId);
		UserModel returnValue = modelMapper.map(foundUser, UserModel.class);
		returnValue.setCompanyId(foundUser.getOwnerOfCompany().getId());
		return returnValue;
	}

}
