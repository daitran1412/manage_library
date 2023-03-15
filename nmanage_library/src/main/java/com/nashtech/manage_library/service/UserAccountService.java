package com.nashtech.manage_library.service;

import java.util.List;

import com.nashtech.manage_library.Entity.Account.UserAccount;


public interface UserAccountService {

    UserAccount createUserAccount(UserAccount UserAccount);

    UserAccount updateUserAccount(UserAccount UserAccount);

    UserAccount getUserAccountById(Long id);

    List<UserAccount> getAllUserAccounts();

    void deleteUserAccount(Long id);
    
}
