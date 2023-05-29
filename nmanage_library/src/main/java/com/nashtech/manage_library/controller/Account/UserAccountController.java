package com.nashtech.manage_library.controller.Account;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.manage_library.Entity.Account.UserAccount;
import com.nashtech.manage_library.Entity.Error.Status;
import com.nashtech.manage_library.dto.Account.UserAccountDto;
import com.nashtech.manage_library.repository.Account.UserAccountRepository;
import com.nashtech.manage_library.security.JwtSecretGenerator;
import com.nashtech.manage_library.service.UserAccountService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/v1/user-accounts")
@CrossOrigin
public class UserAccountController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ModelMapper modelMapper;
    private UserAccountService userAccountService;
    private static final String jwtSecret = JwtSecretGenerator.generateSecret();
    
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllUserAccounts() {
        List<UserAccount> userAccounts = userAccountService.getAllUserAccounts();
        List<UserAccountDto> userAccountDtos = userAccounts.stream().map(userAccount -> modelMapper.map(userAccount, UserAccountDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(userAccountDtos);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<?> getUserAccountById(@PathVariable Long id) {
        UserAccount userAccount = userAccountService.getUserAccountById(id);
        if (userAccount == null) {
            Status status = new Status();
            status.setMessage("UserAccount not found");
            return ResponseEntity.ok(status);
        }
        UserAccountDto userAccountDto = modelMapper.map(userAccount, UserAccountDto.class);
        return ResponseEntity.ok().body(userAccountDto);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUserAccount(@RequestBody UserAccountDto userAccountDto) {
        UserAccount userAccount = modelMapper.map(userAccountDto, UserAccount.class);
        UserAccount createdUserAccount = userAccountService.createUserAccount(userAccount);
        UserAccountDto createdUserAccountDto = modelMapper.map(createdUserAccount, UserAccountDto.class);
        return ResponseEntity.ok().body(createdUserAccountDto);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateUserAccount(@PathVariable Long id, @RequestBody UserAccountDto userAccountDto) {
        UserAccount userAccount = userAccountService.getUserAccountById(id);
        if (userAccount == null) {
            Status status = new Status();
            status.setMessage("UserAccount not found");
            return ResponseEntity.ok(status);
        }
        UserAccount updatedUserAccount = userAccountService.updateUserAccount(userAccount);
        UserAccountDto updatedUserAccountDto = modelMapper.map(updatedUserAccount, UserAccountDto.class);
        return ResponseEntity.ok().body(updatedUserAccountDto);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserAccount(@PathVariable Long id) {
        UserAccount userAccount = userAccountService.getUserAccountById(id);
        if (userAccount == null) {
            Status status = new Status();
            status.setMessage("UserAccount not found");
            return ResponseEntity.ok(status);
        }
        userAccountService.deleteUserAccount(id);
        Status status = new Status();
        status.setMessage("Delete successfully");
        return ResponseEntity.ok(status);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAccount userAccount) {
        // validate username and password
        if (userAccount.getUsername() == null || userAccount.getUsername().isEmpty()
                || userAccount.getPassword() == null
                || userAccount.getPassword().isEmpty()) {
            Status status = new Status();
            status.setMessage("Username and password are required");
            return ResponseEntity.ok(status);
        }
        UserAccount existingUserAccount = userAccountRepository.findByUsernameAndPassword(userAccount.getUsername(),
                userAccount.getPassword());
        if (existingUserAccount == null) {
            Status status = new Status();
            status.setMessage("Username or password is incorrect");
            return ResponseEntity.ok(status);
        }
        String token = Jwts.builder()
                .setSubject(existingUserAccount.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .claim("role", existingUserAccount.getRole())
                .compact();
        existingUserAccount.setToken(token);
        return ResponseEntity.ok(existingUserAccount);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserAccountDto userAccountDto) { 
        if (userAccountDto.getUsername() == null || userAccountDto.getUsername().isEmpty()
                ||userAccountDto.getPassword() == null
                ||userAccountDto.getPassword().isEmpty()
                ||userAccountDto.getUser().getDateOfbirth() == null
                ||userAccountDto.getUser().getUsername() == null
                ||userAccountDto.getUser().getUsername().isEmpty()
                ||userAccountDto.getUser().getEmail() == null
                ||userAccountDto.getUser().getEmail().isEmpty()
                ||userAccountDto.getUser().getPhone() == null
                ||userAccountDto.getUser().getPhone().isEmpty()) {
            Status status = new Status();
            status.setMessage("Information is required");
            return ResponseEntity.ok(status);
        }
        UserAccount existingUserAccount = userAccountRepository.findByUsernameAndPassword(userAccountDto.getUsername(), userAccountDto.getPassword());
        if (existingUserAccount != null) {
            Status status = new Status();
            status.setMessage("Username is already existed");
            return ResponseEntity.ok(status);
        }
        UserAccount userAccount = modelMapper.map(userAccountDto, UserAccount.class);
        UserAccount createdUserAccount = userAccountService.createUserAccount(userAccount);
        UserAccountDto createdUserAccountDto = modelMapper.map(createdUserAccount, UserAccountDto.class);
        return ResponseEntity.ok().body(createdUserAccountDto);
    }

}
