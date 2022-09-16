package gmc.project.blockchain.services;

import gmc.project.blockchain.models.UserModel;

public interface UserService {
	public void save(UserModel userData);
	public UserModel findAUser(String userId);
}
