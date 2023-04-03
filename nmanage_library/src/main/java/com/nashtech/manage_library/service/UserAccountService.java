package com.nashtech.manage_library.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.nashtech.manage_library.Entity.Account.UserAccount;
import com.nashtech.manage_library.Payload.LoginRequest;


public interface UserAccountService {

    UserAccount createUserAccount(UserAccount UserAccount);

    UserAccount updateUserAccount(UserAccount UserAccount);

    UserAccount getUserAccountById(Long id);

    List<UserAccount> getAllUserAccounts();

    void deleteUserAccount(Long id);

    UserDetails loadUserByUsername(String username);

    UserAccount authenticate(UserAccount userAccount) throws Exception;
    
}
