package com.nashtech.manage_library.repository.Publish;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.manage_library.Entity.Publish.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    
    Optional<Publisher> findById(Long publisherId);

    Optional<Publisher> findByName(String publisherName);

    void deleteById(Long publisherId);

}
