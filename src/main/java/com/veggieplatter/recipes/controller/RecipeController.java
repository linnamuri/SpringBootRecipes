package com.veggieplatter.recipes.controller;

import com.veggieplatter.recipes.dtos.RecipeDto;
import com.veggieplatter.recipes.entites.Recipe;
import com.veggieplatter.recipes.repositories.RecipeRepository;
import com.veggieplatter.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @GetMapping("/all")
    public List<RecipeDto> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @PostMapping("/create/{userId}")
    public List<RecipeDto> createRecipe(@RequestBody RecipeDto recipeDto,@PathVariable Long userId){
        return recipeService.addRecipe(recipeDto,userId);
        //return recipeService.getAllRecipes();
    }
    @DeleteMapping("/delete/{id}")
    public List<RecipeDto> deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipeById(id);
        return recipeService.getAllRecipes();

    }
    @PutMapping("/edit")
    public void updateRecipe(@RequestBody RecipeDto recipeDto ){
        recipeService.updateRecipeById(recipeDto);
    }

    //@GetMapping("/byuser/{userId}")
  //  public List<RecipeDto> getRecipesByUser(@PathVariable Long userId){
      //  return recipeService.getAllRecipesByUserId(userId);
   // }

}










