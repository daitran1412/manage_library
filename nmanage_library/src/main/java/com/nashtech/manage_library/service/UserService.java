package com.nashtech.manage_library.service;

import java.util.List;

import com.nashtech.manage_library.Entity.Reader.User;

public interface UserService {

    List <User> getAllUsers ();

    User getUserById (Long id);

    List <User> getUserListByUsername (String fullname);

    User getUserByEmail (String email);

    User createUser (User user);

    User updateUser (User user);

    void deleteUser (Long id);
}
