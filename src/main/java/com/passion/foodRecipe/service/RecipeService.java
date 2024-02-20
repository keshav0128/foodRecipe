package com.passion.foodRecipe.service;

import com.passion.foodRecipe.domain.Recipe;
import com.passion.foodRecipe.domain.User;

import java.util.List;

public interface RecipeService {
   

    Recipe createRecipe(Recipe recipe,User user);

    void deleteRecipeById(Long recipeId) throws Exception;

    List<Recipe> getAllRecipe();

    Recipe findRecipeById(Long recipeId) throws Exception;

    Recipe updateRecipe(Recipe recipe,Long recipeId) throws Exception;

    Recipe updateLike(Long recipeId, User user) throws Exception;
}
