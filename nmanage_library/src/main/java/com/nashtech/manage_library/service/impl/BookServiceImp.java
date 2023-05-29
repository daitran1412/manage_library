package com.nashtech.manage_library.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.nashtech.manage_library.Entity.ListBook.Book;
import com.nashtech.manage_library.Entity.ListBook.Category;
import com.nashtech.manage_library.Entity.Publish.Publisher;
import com.nashtech.manage_library.Entity.Reader.Author;
import com.nashtech.manage_library.dto.ListBook.BookDto;
import com.nashtech.manage_library.repository.ListBook.BookRepository;
import com.nashtech.manage_library.repository.ListBook.CategoryRepository;
import com.nashtech.manage_library.repository.Publish.PublisherRepository;
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
    private PublisherRepository publisherRepository;

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
        book.setPublisher(new Publisher());
        bookDto.getCategoriesName().forEach(category -> {

            // set category for book
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

        // set author for book
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

        // set publisher for book
        Optional<Publisher> existingPublisher = publisherRepository.findByName(bookDto.getPublisher());
        if (existingPublisher.isPresent()) {
            book.setPublisher(existingPublisher.get());
        } else {
            Publisher newPublisher = new Publisher();
            newPublisher.setName(bookDto.getPublisher());
            publisherRepository.save(newPublisher);
            book.setPublisher(newPublisher);
        }
        // Publisher newPublisher = new Publisher();
        // newPublisher.setName(bookDto.getPublisher());
        // publisherRepository.save(newPublisher);
        // book.setPublisher(newPublisher);
    

        return bookRepository.save(book);
    }
    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public void updateBook (Long bookId, BookDto bookDto){
        Book existingBook = bookRepository.findById(bookId).orElse(null);
        if (existingBook != null) {
            existingBook.setName(bookDto.getName());
            existingBook.setAuthor(new HashSet<>());
            existingBook.setCategory(new HashSet<>());
            existingBook.setPublisher(new Publisher());
            existingBook.setDescription(bookDto.getDescription());
            existingBook.setImage(bookDto.getImage());
            existingBook.setQuantity(bookDto.getQuantity());
            existingBook.setStatus(bookDto.getStatus());
            bookDto.getCategoriesName().forEach(category -> {
                Optional<Category> existingCategory = categoryRepository.findByName(category);
                if (existingCategory.isPresent()) {
                    existingBook.getCategory().add(existingCategory.get());
                } else {
                    Category newCategory = new Category();
                    newCategory.setName(category);
                    categoryRepository.save(newCategory);
                    existingBook.getCategory().add(newCategory);
                }
            });
            bookDto.getAuthorsName().forEach( author -> {
                Optional<Author> existingAuthor = authorRepository.findByUsername(author);
                if (existingAuthor.isPresent()){
                    existingBook.getAuthor().add(existingAuthor.get());
                } else {
                    Author newAuthor = new Author();
                    newAuthor.setUsername(author);
                    authorRepository.save(newAuthor);
                    existingBook.getAuthor().add(newAuthor);
                }
            });
            Optional<Publisher> existingPublisher = publisherRepository.findByName(bookDto.getPublisher());
            if (existingPublisher.isPresent()) {
                existingBook.setPublisher(existingPublisher.get());
            } else {
                Publisher newPublisher = new Publisher();
                newPublisher.setName(bookDto.getPublisher());
                publisherRepository.save(newPublisher);
                existingBook.setPublisher(newPublisher);
            }
            bookRepository.save(existingBook);
        }
    }

    @Override
    public void borrowBook(Long bookId) {
        Book existingBook = bookRepository.findById(bookId).orElse(null);
        if (existingBook != null) {
            existingBook.setQuantity(existingBook.getQuantity() - 1);
            bookRepository.save(existingBook);
        }
    }
    
}
