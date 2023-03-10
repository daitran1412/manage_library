package com.nashtech.manage_library.dto.Reader;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.nashtech.manage_library.dto.Account.UserAccount;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String address;
    private UserAccount userAccount;

}
