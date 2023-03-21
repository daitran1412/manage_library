package com.nashtech.manage_library.repository.ListBook;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.manage_library.Entity.ListBook.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(Long id);

    void deleteById(Long id);

    List<Book> findByAuthor(String author);

    List<Book> findByCategory( String category);

    List<Book> findByPublisher(String publisher);

    List<Book> findByStatus(String status);

}
