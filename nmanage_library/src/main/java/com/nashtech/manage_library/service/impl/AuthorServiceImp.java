package com.nashtech.manage_library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.manage_library.Entity.Reader.Author;
import com.nashtech.manage_library.repository.Reader.AuthorRepository;
import com.nashtech.manage_library.service.AuthorService;

@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    @Override
    public Author getAuthorByPhone(String phone) {
        return authorRepository.findByPhone(phone).orElse(null);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor( Author author) {
        Author existingAuthor = authorRepository.findById(author.getId()).orElse(null);
        existingAuthor.setUsername(author.getUsername());
        existingAuthor.setPhone(author.getPhone());
        existingAuthor.setAddress(author.getAddress());
        return authorRepository.save(existingAuthor);
    }

    @Override
    public List<Author> getAuthorsByName(String name) {
        return (List<Author>) authorRepository.findByUsername(name).orElse(null);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

}
