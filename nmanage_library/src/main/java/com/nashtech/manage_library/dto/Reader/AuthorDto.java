package com.nashtech.manage_library.dto.Reader;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto extends PersonDto {
    
    private String phone;
    private boolean verification;

}
