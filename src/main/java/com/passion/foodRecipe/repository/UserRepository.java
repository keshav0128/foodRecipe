package com.passion.foodRecipe.repository;

import com.passion.foodRecipe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserEmail(String userEmail);

}
