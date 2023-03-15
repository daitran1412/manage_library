package com.nashtech.manage_library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.manage_library.Entity.Account.UserAccount;
import com.nashtech.manage_library.repository.Account.UserAccountRepository;
import com.nashtech.manage_library.service.UserAccountService;

@Service
public class UserAccountServiceImp implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserAccount createUserAccount(UserAccount UserAccount) {
        return userAccountRepository.save(UserAccount);
    }

    @Override
    public UserAccount updateUserAccount(UserAccount UserAccount){ 
        UserAccount existingUserAccount = userAccountRepository.findById(UserAccount.getUser().getId()).orElse(null);
        existingUserAccount.setUsername(UserAccount.getUsername());
        existingUserAccount.setPassword(UserAccount.getPassword());
        existingUserAccount.setRole(UserAccount.getRole());
        existingUserAccount.setStatus(UserAccount.getStatus());
        return userAccountRepository.save(existingUserAccount);
    }

    @Override
    public UserAccount getUserAccountById(Long user_id) {
        for (UserAccount userAccount : userAccountRepository.findAll()) {
            if (userAccount.getUser().getId() == user_id) {
                return userAccount;
            }
        }
        return null;
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    @Override
    public void deleteUserAccount(Long id) {
        userAccountRepository.deleteById(id);
    }
    
}
