package com.nashtech.manage_library.dto.Publish;

import com.nashtech.manage_library.dto.Reader.UserDto;
import com.nashtech.manage_library.dto.ListBook.BookDto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRecordDto {
    
    private Long id;
    private String user;
    private String book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;
    private UUID borrowCode;

}
