package com.passion.foodRecipe.controller;

import com.passion.foodRecipe.domain.User;
import com.passion.foodRecipe.repository.UserRepository;
import com.passion.foodRecipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /*@PostMapping("/user")
    public User createUser(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }*/

    /*@DeleteMapping("/user/{userId}")
    public void deleteUserById(@PathVariable Long userId) throws Exception {
        userService.deleteUserById(userId);
    }

    @GetMapping("/user")
    public List<User> getUser(){
       return userService.getAllUser();
    }

    @GetMapping("/user/{userId}")
    public User getUserById(Long userId) throws Exception {
        return userService.findUserById(userId);
    }*/

    @GetMapping("/user/{userId}")
    public User getUserById(Long userId) throws Exception {
        return userService.findUserById(userId);
    }

    //getting user from jwt
    @GetMapping("/api/users/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwt(jwt);
        return user;
    }


}
