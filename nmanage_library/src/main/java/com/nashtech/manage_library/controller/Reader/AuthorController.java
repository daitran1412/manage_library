package com.nashtech.manage_library.controller.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.manage_library.Entity.Error.Status;
import com.nashtech.manage_library.Entity.Reader.Author;
import com.nashtech.manage_library.dto.Reader.AuthorDto;
import com.nashtech.manage_library.service.AuthorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/author")
@CrossOrigin
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    private ModelMapper modelMapper;

    public AuthorController(AuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        log.info("Get all authors1: {}", authors);
        List<AuthorDto> authorDtos = authors.stream().map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toList());
                log.info("Get all authors2: {}", authorDtos);
        return ResponseEntity.ok().body(authorDtos);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            Status status = new Status();
            status.setMessage("Author not found");
            return ResponseEntity.ok(status);
        }
        AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
        return ResponseEntity.ok().body(authorDto);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorService.createAuthor(author);
        AuthorDto authorDtoCreate = modelMapper.map(author, AuthorDto.class);
        return ResponseEntity.ok().body(authorDtoCreate);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        Author authorcheck = authorService.getAuthorById(id);
        if (authorcheck == null) {
            Status status = new Status();
            status.setMessage("Author not found");
            return ResponseEntity.ok(status);
        }
        Author author = modelMapper.map(authorDto, Author.class);
        Author authorUpdate = authorService.updateAuthor(author);
        AuthorDto authorDtoUpdate = modelMapper.map(authorUpdate, AuthorDto.class);
        return ResponseEntity.ok().body(authorDtoUpdate);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            Status status = new Status();
            status.setMessage("Author not found");
            return ResponseEntity.ok(status);
        }
        authorService.deleteAuthor(id);
        Status status = new Status();
        status.setMessage("Delete author successfully");
        return ResponseEntity.ok(status);
    }
    
}
