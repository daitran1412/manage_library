package com.nashtech.manage_library.dto.Account;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    
    private String id;
    private String username;
    private String password;
    private String role;
    private String status;

}
