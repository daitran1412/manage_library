package com.nashtech.manage_library.repository;

import com.nashtech.manage_library.Entity.Reader.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String id);
    User findByUsername(String username);
    User findByEmail(String email);
    void deleteById(String id);
}