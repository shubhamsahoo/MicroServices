package com.techouts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.techouts.bo.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public List<User> findByUserName(String userName);

	@Query(countQuery = "select count(*) from user u where u.userName = ?1 and u.password in (?2)")
	long countByUserNameAndPassword(String userName, String password);
}
