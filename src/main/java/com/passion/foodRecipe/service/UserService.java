package com.passion.foodRecipe.service;

import com.passion.foodRecipe.domain.User;

import java.util.List;

public interface UserService {
    /*User createUser(User user) throws Exception;*/

    /*void deleteUserById(Long userId);

    List<User> getAllUser();

//    User getUserById(Long userId) throws Exception;
    */
    User findUserById(Long userId) throws Exception;

    User findUserByJwt(String jwt) throws Exception;



}
