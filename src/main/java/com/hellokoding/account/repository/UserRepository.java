package com.hellokoding.account.repository;

import com.hellokoding.account.model.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    Page<User> findAllByOrderByIdDesc(Pageable page);
    
    List<User> findAllByOrderByIdDesc();
}
