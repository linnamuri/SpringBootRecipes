package com.veggieplatter.recipes.services;

import com.veggieplatter.recipes.dtos.RecipeDto;
import com.veggieplatter.recipes.dtos.UserDto;

import javax.transaction.Transactional;
import java.util.List;

public interface RecipeService {

    List<RecipeDto> getAllRecipes();

    @Transactional
    List<RecipeDto> addRecipe(RecipeDto recipeDto, Long userId);

    @Transactional
    void updateRecipeById(RecipeDto recipeDto);

    @Transactional
    public void deleteRecipeById(Long Id);

}
