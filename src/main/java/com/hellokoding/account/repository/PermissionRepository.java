package com.hellokoding.account.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hellokoding.account.model.Permission;



@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

	
	Page<Permission> findAllByOrderByIdAsc(Pageable page);
	
	List<Permission> findAllByOrderByIdDesc();

	@Query(value = "SELECT * FROM Permission WHERE username=?1 AND url LIKE '%adminhome%'", nativeQuery = true)
	List<Permission> getUserByuserName(String userName);
	
	
	@Query(value = "SELECT * FROM Permission", nativeQuery = true)
	List<Permission> getPermissionList();

}
