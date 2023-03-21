package com.nashtech.manage_library.dto.Reader;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends PersonDto {
    
    private String email;
    private String phone;

}

