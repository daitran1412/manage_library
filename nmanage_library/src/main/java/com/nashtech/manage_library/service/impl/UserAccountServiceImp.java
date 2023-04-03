package com.nashtech.manage_library.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nashtech.manage_library.Entity.Account.UserAccount;
import com.nashtech.manage_library.repository.Account.UserAccountRepository;
import com.nashtech.manage_library.security.JwtSecretGenerator;
import com.nashtech.manage_library.service.UserAccountService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;

@Service
public class UserAccountServiceImp implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    private static final String jwtSecret = JwtSecretGenerator.generateSecret();

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

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        UserAccount userAccount = userAccountRepository.findByUsername(username).orElseThrow(username);
        return UserDetailsImpl.build(userAccount);
    }

    @Override
    public UserAccount authenticate(UserAccount userAccount) throws Exception {
        // validate username and password
        if (userAccount.getUsername() == null || userAccount.getUsername().isEmpty() || userAccount.getPassword() == null
                || userAccount.getPassword().isEmpty()) {
            throw new Exception("Invalid username or password");
        }
        UserAccount existingUserAccount = userAccountRepository.findByUsernameAndPassword(userAccount.getUsername(), userAccount.getPassword());
        if (existingUserAccount == null) {
            throw new Exception("Invalid username or password");
        }
        String token = Jwts.builder()
                        .setSubject(existingUserAccount.getUsername())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                        .signWith(SignatureAlgorithm.HS512, jwtSecret)
                        .compact();
        existingUserAccount.setToken(token);
        return existingUserAccount;
    }
    
    
}
