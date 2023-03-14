package com.nashtech.manage_library.repository.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.manage_library.Entity.Account.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
    UserAccount findByUsername(String username);
    
    UserAccount findByUsernameAndPassword(String username, String password);

}
