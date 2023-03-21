package com.nashtech.manage_library.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.nashtech.manage_library.Entity.ListBook.Book;
import com.nashtech.manage_library.Entity.ListBook.Category;
import com.nashtech.manage_library.Entity.Reader.Author;
import com.nashtech.manage_library.dto.ListBook.BookDto;
import com.nashtech.manage_library.dto.ListBook.CategoryDto;
import com.nashtech.manage_library.dto.Reader.AuthorDto;
import com.nashtech.manage_library.repository.ListBook.BookRepository;
import com.nashtech.manage_library.repository.ListBook.CategoryRepository;
import com.nashtech.manage_library.repository.Reader.AuthorRepository;
import com.nashtech.manage_library.service.BookService;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

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
        book.setAuthor(new HashSet<>());
        bookDto.getCategoriesName().forEach(category -> {
            Optional<Category> existingCategory = categoryRepository.findByName(category);
            if (existingCategory.isPresent()) {
                book.getCategory().add(existingCategory.get());
            } else {
                Category newCategory = new Category();
                newCategory.setName(category);
                categoryRepository.save(newCategory);
                book.getCategory().add(newCategory);
            }
        });
        bookDto.getAuthorsName().forEach( author -> {
            Optional<Author> existingAuthor = authorRepository.findByUsername(author);
            if (existingAuthor.isPresent()){
                book.getAuthor().add(existingAuthor.get());
            } else {
                Author newAuthor = new Author();
                newAuthor.setUsername(author);
                authorRepository.save(newAuthor);
                book.getAuthor().add(newAuthor);
            }
        });
        return bookRepository.save(book);
    }
    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
    
}
