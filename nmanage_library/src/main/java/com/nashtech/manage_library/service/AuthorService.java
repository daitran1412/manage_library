package com.nashtech.manage_library.service;

import java.util.List;

import com.nashtech.manage_library.Entity.Reader.Author;

public interface AuthorService {

    List <Author> getAllAuthors ();

    Author getAuthorById (Long authorId);

    Author getAuthorByPhone (String phone);

    Author createAuthor (Author author);

    Author updateAuthor (Author author);

    Author getAuthorsByName (String name);

    void deleteAuthor (Long authorId);
        
}
