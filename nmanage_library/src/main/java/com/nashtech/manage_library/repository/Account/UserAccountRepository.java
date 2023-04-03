package com.nashtech.manage_library.repository.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.manage_library.Entity.Account.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
    UserAccount findByUsername(String username);
    
    UserAccount findByUsernameAndPassword(String username, String password);

}
