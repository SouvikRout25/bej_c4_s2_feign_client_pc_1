package com.example.UserAuth.services;

import com.example.UserAuth.domain.User;
import com.example.UserAuth.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    public User addUser(User user);
    public List<User> getAllUser();
    public boolean deleteUser(int userId) throws UserNotFoundException;
    public User findByUserIdAndPassword(int userId, String password) throws UserNotFoundException;
}