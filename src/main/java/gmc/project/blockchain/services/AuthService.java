package gmc.project.blockchain.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import gmc.project.blockchain.entities.UserEntity;

public interface AuthService extends UserDetailsService {
	UserEntity findByUserName(String userName);
}
