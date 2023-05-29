package com.nashtech.manage_library.dto.ListBook;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Set;

import com.nashtech.manage_library.dto.ListBook.BookDto;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private Set<BookDto> book;
    
}
