package com.nashtech.manage_library.service;

import java.util.List;

import com.nashtech.manage_library.Entity.ListBook.Book;
import com.nashtech.manage_library.dto.ListBook.BookDto;

public interface BookService {

    List <Book> getAllBooks ();
    
    List <Book> getBooksByAuthor (String author);

    List <Book> getBooksByCategory (String category);

    List <Book> getBooksByPublisher (String publisher);

    List <Book> getBooksByStatus (String status);

    Book getBookById (Long bookId);

    Book createBook (BookDto bookDto);    

    void deleteBook (Long bookId);

    void updateBook (Long bookId, BookDto bookDto);

    void borrowBook (Long bookId);
    
}
