package com.nashtech.manage_library.controller.ListBook;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.manage_library.Entity.ListBook.Book;
import com.nashtech.manage_library.dto.ListBook.BookDto;
import com.nashtech.manage_library.service.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    
    @Autowired
    private ModelMapper modelMapper;
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get")
    public List<BookDto> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = books.stream().map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @GetMapping("/get/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }

    @PostMapping("/create")
    public BookDto createBook(@RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        Book createdBook = bookService.createBook(book);
        BookDto createdBookDto = modelMapper.map(createdBook, BookDto.class);
        return createdBookDto;
    }

    @PostMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    

}
