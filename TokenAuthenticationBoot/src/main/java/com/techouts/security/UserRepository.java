package com.techouts.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techouts.bo.UserBO;

@Repository
public interface UserRepository extends CrudRepository<UserBO, Long> {
	@Query(value = "SELECT u FROM user u WHERE u.userName = ?1 and u.password = ?2")
	Optional<UserBO> findByUserNameAndPassword(String userName, String password);

	Optional<UserBO> findByAccessToken(String accessToken);
}
