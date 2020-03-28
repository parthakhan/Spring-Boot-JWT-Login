package com.partha.library.repository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.partha.library.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u  WHERE u.username=:username")
    User findByUsername( @Param("username") String username);
}
