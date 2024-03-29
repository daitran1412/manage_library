package com.nashtech.manage_library.dto.Account;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.nashtech.manage_library.dto.Reader.UserDto;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDto {
    
    private Long id;
    private String username;
    private String password;
    private String role;
    private String status;
    private UserDto user;

    public UserAccountDto(String username, String password, String role, String status, UserDto user) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.user = user;
    }
}
