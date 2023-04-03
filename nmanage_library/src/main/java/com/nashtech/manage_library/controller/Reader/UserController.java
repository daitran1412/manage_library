package com.nashtech.manage_library.controller.Reader;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

import com.nashtech.manage_library.service.UserService;
import com.nashtech.manage_library.dto.Reader.UserDto;
import com.nashtech.manage_library.Entity.Reader.User;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    @Autowired
    private ModelMapper modelMapper;
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/get")
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = users.stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return userDtos;
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "id") Long userId) {
        User user = userService.getUserById(userId);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/get/fullname/{fullname}")
    public ResponseEntity<List<UserDto>> getUserByUsername(@PathVariable(value = "fullname") String fullname) {
        List<User> users = userService.getUserListByUsername(fullname); // Sửa đổi phương thức trong service để trả về danh sách các User thay vì User duy nhất
        List<UserDto> userDtos = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/get/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable(value = "email") String email) {
        User user = userService.getUserByEmail(email);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User newUser = userService.createUser(user);
        UserDto newUserDto = modelMapper.map(newUser, UserDto.class);
        return ResponseEntity.ok().body(newUserDto);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id") Long userId, @RequestBody UserDto userDto) {
        if (!userId.equals(userDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        User user = modelMapper.map(userDto, User.class);
        User updatedUser = userService.updateUser(user);
        UserDto updatedUserDto = modelMapper.map(updatedUser, UserDto.class);
        return ResponseEntity.ok().body(updatedUserDto);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable(value = "id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}
