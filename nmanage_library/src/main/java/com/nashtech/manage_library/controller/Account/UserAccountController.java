package com.nashtech.manage_library.controller.Account;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.manage_library.Entity.Account.UserAccount;
import com.nashtech.manage_library.dto.Account.UserAccountDto;
import com.nashtech.manage_library.service.UserAccountService;

@RestController
@RequestMapping("/api/v1/user-accounts")
public class UserAccountController {

    @Autowired
    private ModelMapper modelMapper;
    private UserAccountService userAccountService;
    
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/get")
    public List<UserAccountDto> getAllUserAccounts() {
        List<UserAccount> userAccounts = userAccountService.getAllUserAccounts();
        List<UserAccountDto> userAccountDtos = userAccounts.stream().map(userAccount -> modelMapper.map(userAccount, UserAccountDto.class))
                .collect(Collectors.toList());
        return userAccountDtos;
    }

    @GetMapping("/get/id/{id}")
    public UserAccountDto getUserAccountById(@PathVariable Long id) {
        UserAccount userAccount = userAccountService.getUserAccountById(id);
        UserAccountDto userAccountDto = modelMapper.map(userAccount, UserAccountDto.class);
        return userAccountDto;
    }

    @PostMapping("/create")
    public UserAccountDto createUserAccount(@RequestBody UserAccountDto userAccountDto) {
        UserAccount userAccount = modelMapper.map(userAccountDto, UserAccount.class);
        UserAccount createdUserAccount = userAccountService.createUserAccount(userAccount);
        UserAccountDto createdUserAccountDto = modelMapper.map(createdUserAccount, UserAccountDto.class);
        return createdUserAccountDto;
    }

    @PostMapping("/update/{id}")
    public UserAccountDto updateUserAccount(@PathVariable Long id, @RequestBody UserAccountDto userAccountDto) {
        UserAccount userAccount = modelMapper.map(userAccountDto, UserAccount.class);
        UserAccount updatedUserAccount = userAccountService.updateUserAccount(userAccount);
        UserAccountDto updatedUserAccountDto = modelMapper.map(updatedUserAccount, UserAccountDto.class);
        return updatedUserAccountDto;
    }

    @PostMapping("/delete/{id}")
    public void deleteUserAccount(@PathVariable Long id) {
        userAccountService.deleteUserAccount(id);
    }
    
}
