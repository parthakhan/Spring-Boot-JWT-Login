package com.partha.library.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.partha.library.model.Role;
import com.partha.library.model.User;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{
	   
}
