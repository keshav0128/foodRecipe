package com.passion.foodRecipe.controller;

import com.passion.foodRecipe.domain.Recipe;
import com.passion.foodRecipe.domain.User;
import com.passion.foodRecipe.service.RecipeService;
import com.passion.foodRecipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    @Autowired
    private RecipeService  recipeService;

    @Autowired
    private UserService userService;
    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe,
                               @PathVariable Long userId)
                                throws Exception {
        return recipeService.createRecipe(recipe, userService.findUserById(userId));
    }

    @GetMapping("/get-recipe/{recipeId}")
    public Recipe findRecipe(@PathVariable Long recipeId) throws Exception {
        return recipeService.findRecipeById(recipeId);
    }

    @DeleteMapping("/delete-recipe/{recipeId}")
    public void deleteRecipeById(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipeById(recipeId);
    }

    @GetMapping("/get-recipe")
    public List<Recipe> getRecipe(){
        return recipeService.getAllRecipe();
    }

    @PutMapping("/update-recipe/{recipeId}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long recipeId) throws Exception{
        return recipeService.updateRecipe(recipe, recipeId);
    }

    @PutMapping("/like/{recipeId}/{userId}")
    public Recipe likedRecipe(@PathVariable Long recipeId,
                              @PathVariable Long userId)
                                throws Exception {
        return recipeService.updateLike(recipeId,userService.findUserById(userId));
    }
}
