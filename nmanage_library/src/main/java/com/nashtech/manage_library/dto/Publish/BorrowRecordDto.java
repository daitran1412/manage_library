package com.nashtech.manage_library.dto.Publish;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRecordDto {
    
    private Long id;
    private String user;
    private String book;
    private String borrowDate;
    private String returnDate;
    private String status;

}
