package com.nashtech.manage_library.controller.ListBook;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.manage_library.Entity.Error.Status;
import com.nashtech.manage_library.Entity.ListBook.Book;
import com.nashtech.manage_library.dto.ListBook.BookDto;
import com.nashtech.manage_library.service.BookService;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin
public class BookController {
    
    @Autowired
    private ModelMapper modelMapper;
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = books.stream().map(book -> {
            BookDto bookDto = modelMapper.map(book, BookDto.class);
            bookDto.setAuthorsName(book.getAuthor()
            .stream().map(author -> author.getUsername()).collect(Collectors.toSet()));
            bookDto.setCategoriesName(book.getCategory()
            .stream().map(category -> category.getName()).collect(Collectors.toSet()));
            return bookDto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookDtos);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        if (book == null) {
            return ResponseEntity.ok().body("Book not found");
        }
        bookDto.setAuthorsName(book.getAuthor()
        .stream().map(author -> author.getUsername()).collect(Collectors.toSet()));
        bookDto.setCategoriesName(book.getCategory()
        .stream().map(category -> category.getName()).collect(Collectors.toSet()));
        return ResponseEntity.ok().body(bookDto);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody BookDto bookDto) {
        if (bookDto.getName() == null || bookDto.getName().isEmpty()
        || bookDto.getAuthorsName() == null || bookDto.getAuthorsName().isEmpty()
        || bookDto.getPublisher() == null || bookDto.getPublisher().isEmpty()
        || bookDto.getCategoriesName() == null || bookDto.getCategoriesName().isEmpty()
        || bookDto.getDescription() == null || bookDto.getDescription().isEmpty()
        || Integer.valueOf(bookDto.getQuantity()) == null || bookDto.getQuantity() <= 0) 
        {
            Status status = new Status();
            status.setMessage("Invalid input");
            return ResponseEntity.ok().body(status);
        }
        Book creBook = bookService.createBook(bookDto);
        BookDto creBookDto = modelMapper.map(creBook, BookDto.class);
        return ResponseEntity.ok().body(creBookDto); 
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.ok().body("Book not found");
        }
        bookService.deleteBook(id);
        Status status = new Status();
        status.setMessage("Delete book successfully");
        return ResponseEntity.ok().body(status);
    }

    @PostMapping("/borrow/{id}")
    public ResponseEntity<?> borrowBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.ok().body("Book not found");
        }
        if (book.getQuantity() == 0) {
            Status status = new Status();
            status.setMessage("Book is out of stock");
            return ResponseEntity.ok().body(status);
        }
        bookService.borrowBook(id);
        Status status = new Status();
        status.setMessage("Borrow book successfully");
        return ResponseEntity.ok().body(status);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.ok().body("Book not found");
        }
        if (bookDto.getName() == null || bookDto.getName().isEmpty()
        || bookDto.getAuthorsName() == null || bookDto.getAuthorsName().isEmpty()
        || bookDto.getPublisher() == null || bookDto.getPublisher().isEmpty()
        || bookDto.getCategoriesName() == null || bookDto.getCategoriesName().isEmpty()
        || bookDto.getDescription() == null || bookDto.getDescription().isEmpty()
        || Integer.valueOf(bookDto.getQuantity()) == null || bookDto.getQuantity() <= 0) 
        {
            Status status = new Status();
            status.setMessage("Invalid input");
            return ResponseEntity.ok().body(status);
        }
        bookService.updateBook(id, bookDto);
        Status status = new Status();
        status.setMessage("Update book successfully");
        return ResponseEntity.ok().body(status);
    }
    
}
