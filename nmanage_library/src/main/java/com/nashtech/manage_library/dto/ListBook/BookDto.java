package com.nashtech.manage_library.dto.ListBook;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String id;
    private String name;
    private String author;
    private String publisher;
    private String category;
    private String status;
    private String quantity;
    private String description;
    private String image;
    
}
