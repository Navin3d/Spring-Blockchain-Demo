package gmc.project.blockchain.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.blockchain.entities.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, String> {
	Optional<UserEntity> findByUserName(String userName);
	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByMobileNumber(String mobileNumber);
}
