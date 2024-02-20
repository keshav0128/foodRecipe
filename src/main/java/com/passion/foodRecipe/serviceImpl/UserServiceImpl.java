package com.passion.foodRecipe.serviceImpl;

import com.passion.foodRecipe.Config.JwtProvider;
import com.passion.foodRecipe.domain.User;
import com.passion.foodRecipe.repository.UserRepository;
import com.passion.foodRecipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;


  /*  @Override
    public User createUser(User user) throws Exception {
       *//* if (userRepository.findByUserEmail(user.getUserEmail())) {
            throw new Exception("Email address already exists");
        }*//*
        return userRepository.save(user);
    }*/
    /*@Override
    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    */
  @Override
  public User findUserById(Long userId) throws Exception {
      Optional<User> userOptional = userRepository.findById(userId);
      if(userOptional.isPresent()){
          return userOptional.get();
      }
      throw new Exception("User doesn't exists");
  }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String userEmail = jwtProvider.getEmailFromJwtToken(jwt);
        if(userEmail == null){
            throw new Exception("Invalid jwt token");
        }
        User user = userRepository.findByUserEmail(userEmail);
        if(user == null){
            throw new Exception(" User not found with email");

        }

        return user;
    }

}
