package com.passion.foodRecipe.controller;

import com.passion.foodRecipe.AuthResponse;
import com.passion.foodRecipe.Config.JwtProvider;
import com.passion.foodRecipe.LoginRequest;
import com.passion.foodRecipe.domain.User;
import com.passion.foodRecipe.repository.UserRepository;
import com.passion.foodRecipe.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
   @Autowired
    private UserRepository userRepository;
   @Autowired
    private CustomUserDetailService customUserDetailService;
   @Autowired
    private JwtProvider jwtProvider;
   @Autowired
   private PasswordEncoder passwordEncoder;
@PostMapping("/signup")
   public AuthResponse createUser(@RequestBody User user) throws Exception{
        String userEmail = user.getUserEmail();
        String userPassword = user.getUserPassword();
        String userName = user.getUserName();
        String userNationality = user.getUserNationality();

        User isExistEmail = userRepository.findByUserEmail(userEmail);
        if(isExistEmail != null){
            throw new Exception("Email is already used with another accpunt");
        }
    User createdUser = new User();
        createdUser.setUserEmail(userEmail);
        createdUser.setUserPassword(passwordEncoder.encode(userPassword));
        createdUser.setUserName(userName);
        createdUser.setUserNationality(userNationality);
        User saveUser = userRepository.save(createdUser);

    Authentication authentication = new UsernamePasswordAuthenticationToken(userEmail, userPassword);
       SecurityContextHolder.getContext().setAuthentication(authentication);
       String token = jwtProvider.generateToken(authentication);

   AuthResponse res = new AuthResponse();
    res.setJwt(token);
    res.setMessage("sign up sucess");
    return res;
   }

   @PostMapping("/siginin")
   public AuthResponse signinhandler(@RequestBody LoginRequest loginRequest)throws Exception{
        String userName = loginRequest.getUserEmail();
        String userPassword = loginRequest.getUserPassword();

       Authentication authentication = authenticate(userName, userPassword);
       SecurityContextHolder.getContext().setAuthentication(authentication);
       String token = jwtProvider.generateToken(authentication);

       AuthResponse res = new AuthResponse();
       res.setJwt(token);
       res.setMessage("sign In  successfully");
       return res;
   }

    private Authentication authenticate(String userName, String userPassword) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
            if(userDetails == null){
                throw new BadCredentialsException("user not found");
            }
            if(!passwordEncoder.matches(userPassword, userDetails.getPassword())) {
                throw new BadCredentialsException("username or password is incorrect");
            }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
