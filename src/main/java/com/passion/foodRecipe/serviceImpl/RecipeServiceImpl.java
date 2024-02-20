package com.passion.foodRecipe.serviceImpl;

import com.passion.foodRecipe.domain.Recipe;
import com.passion.foodRecipe.domain.User;
import com.passion.foodRecipe.repository.RecipeRepository;
import com.passion.foodRecipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;


    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe newRecipe = new Recipe();
            newRecipe.setRecipeTitle(recipe.getRecipeTitle());
            newRecipe.setRecipeDescription(recipe.getRecipeDescription());
            newRecipe.setRecipeImage(recipe.getRecipeImage());
            newRecipe.setUser(user);
            newRecipe.setVegeterian(recipe.isVegeterian());
            newRecipe.setRecipeCreatedAt(LocalDateTime.now());
        return recipeRepository.save(newRecipe);
    }

    @Override
    public void deleteRecipeById(Long recipeId) throws Exception {
        findRecipeById(recipeId);
        recipeRepository.deleteById(recipeId);
    }

    @Override
    public List<Recipe> getAllRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findRecipeById(Long recipeId) throws Exception {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(recipeOptional.isPresent())
        {
            return recipeOptional.get();
        }
        throw  new Exception("Recipe doesn't exists with ths id "+ recipeId);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long recipeId) throws Exception {
        Recipe toUpdateRecipe = findRecipeById(recipeId);
        if(recipe.getRecipeTitle() != null){
            toUpdateRecipe.setRecipeTitle(recipe.getRecipeTitle());
        }
        if(recipe.getRecipeDescription() != null){
            toUpdateRecipe.setRecipeDescription(recipe.getRecipeDescription());
        }
        if(recipe.getRecipeImage() != null){
            toUpdateRecipe.setRecipeImage(recipe.getRecipeImage());
        }
        if(recipe.getRecipeCreatedAt() != null){
            toUpdateRecipe.setRecipeCreatedAt(LocalDateTime.now());
        }
        return recipeRepository.save(toUpdateRecipe);
    }

    @Override
    public Recipe updateLike(Long recipeId, User user) throws Exception {
        Recipe rec = findRecipeById(recipeId);
        if(rec.getLikes().contains(user.getUserId())){
            rec.getLikes().remove(user.getUserId());
        }
        else rec.getLikes().add(user.getUserId());
        return recipeRepository.save(rec);
    }
}
