package com.nashtech.manage_library.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import com.nashtech.manage_library.Entity.ListBook.Book;
import com.nashtech.manage_library.Entity.ListBook.Category;
import com.nashtech.manage_library.dto.ListBook.BookDto;
import com.nashtech.manage_library.repository.ListBook.BookRepository;
import com.nashtech.manage_library.repository.ListBook.CategoryRepository;
import com.nashtech.manage_library.service.BookService;
import com.nashtech.manage_library.service.CategoryService;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modalMapper;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    @Override
    public List<Book> getBooksByPublisher(String publisher) {
        return bookRepository.findByPublisher(publisher);
    }

    @Override
    public List<Book> getBooksByStatus(String status) {
        return bookRepository.findByStatus(status);
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public Book createBook(BookDto bookDto) {
        Book book = modalMapper.map(bookDto, Book.class);
        book.setCategory(new HashSet<>());
        bookDto.getCategoriesName().forEach(category -> {
            Optional<Category> existingCategory = categoryRepository.findByName(category);
            System.out.println("existingCategory: " + existingCategory.isPresent());
            if (existingCategory.isPresent()) {
                book.getCategory().add(existingCategory.get());

            } else {
                Category newCategory = new Category();
                newCategory.setName(category);
                categoryRepository.save(newCategory);
                book.getCategory().add(newCategory);
            }
        });
        return bookRepository.save(book);
    }
    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
    
}
