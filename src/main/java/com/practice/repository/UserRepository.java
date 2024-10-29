package com.practice.repository;

import com.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String > {

    Optional<User> findUserByEmail(String email);

    Optional<User> findByUsername(String username);
}
