package com.nashtech.manage_library.controller.Reader;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.manage_library.Entity.Reader.Author;
import com.nashtech.manage_library.dto.Reader.AuthorDto;
import com.nashtech.manage_library.service.AuthorService;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    private ModelMapper modelMapper;

    public AuthorController(AuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/get")
    public List<AuthorDto> getAllAuthor() {
        List<AuthorDto> authorDtos = new ArrayList<>();
        List<Author> authors = authorService.getAllAuthors();
        for (Author author : authors) {
            authorDtos.add(modelMapper.map(author, AuthorDto.class));
        }
        return authorDtos;
    }

    @GetMapping("/get/{id}")
    public AuthorDto getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        return modelMapper.map(author, AuthorDto.class);
    }

    @PostMapping("/create")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorService.createAuthor(author);
        return modelMapper.map(author, AuthorDto.class);
    }

    @PostMapping("/update/{id}")
    public AuthorDto updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorService.updateAuthor(author);
        return modelMapper.map(author, AuthorDto.class);
    }

    @PostMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
    
}
