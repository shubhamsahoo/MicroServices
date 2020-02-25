package com.techouts.security;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techouts.bo.UserBO;

@Repository
public interface UserRepository extends CrudRepository<UserBO, Long> {

	public List<UserBO> findByUserName(String userName);

	@Query(value = "select * from user u where u.user_name = ?1 and u.password in (?2)", nativeQuery = true)
	UserBO getUserByUserNameAndPassword(String userName, String password);

	@Query(value = "select * from user u where u.access_token = ?1", nativeQuery = true)
	UserBO getUserByAccessToken(String accessToken);

	@Query(countQuery = "select count(*) from user u where u.userName = ?1 and u.password in (?2)")
	long countByUserNameAndPassword(String userName, String password);
}
