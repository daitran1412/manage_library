package com.nashtech.manage_library.repository.Reader;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.manage_library.Entity.Reader.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findById(Long id);

    Optional<Author> findByUsername(String username);

    Optional<Author> findByPhone(String phone);

    void deleteById(Long id);
    
}
