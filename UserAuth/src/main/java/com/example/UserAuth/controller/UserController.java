package com.example.UserAuth.controller;

import com.example.UserAuth.domain.User;
import com.example.UserAuth.exception.UserNotFoundException;
import com.example.UserAuth.services.SecurityTokenGenerator;
import com.example.UserAuth.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("userauth/user")
public class UserController {

    private UserServiceImpl userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserServiceImpl userService, SecurityTokenGenerator securityTokenGenerator){
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> insertUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {

        Map<Integer,String> map = null;
        try {
            User user1 = userService.findByUserIdAndPassword(user.getUserId(), user.getPassword());
            if(user1.getUserId() == user.getUserId()){
                map = securityTokenGenerator.generateToken(user);
            }
            return new ResponseEntity<>(map,HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new UserNotFoundException();
        }catch (Exception e){
            return new ResponseEntity<>("Try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> fetchUser(){
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) throws UserNotFoundException {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
}
