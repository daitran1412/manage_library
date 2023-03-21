package com.nashtech.manage_library.dto.ListBook;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Set;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String id;
    private String name;
    Set <String> authorsName;
    private String publisher;
    Set <String> categoriesName;
    private String status;
    private String quantity;
    private String description;
    private String image;
    
}
