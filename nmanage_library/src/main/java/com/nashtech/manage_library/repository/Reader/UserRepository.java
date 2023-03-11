package com.nashtech.manage_library.repository.Reader;

import com.nashtech.manage_library.Entity.Reader.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    @Query("SELECT u FROM User u WHERE u.username = :username")
    List <User> findByUsername(@Param("username") String username);
    User findByEmail(String email);
    void deleteById(Long id);
}