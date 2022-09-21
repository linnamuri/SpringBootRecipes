package com.veggieplatter.recipes.controller;

import com.veggieplatter.recipes.dtos.RecipeDto;
import com.veggieplatter.recipes.entites.Recipe;
import com.veggieplatter.recipes.repositories.RecipeRepository;
import com.veggieplatter.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @GetMapping("/getallrecipes")
    public List<RecipeDto> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @PostMapping("/createrecipe/{userId}")
    public List<RecipeDto> createRecipe(@RequestBody RecipeDto recipeDto,@PathVariable Long userId){
        return recipeService.addRecipe(recipeDto,userId);
        //return recipeService.getAllRecipes();
    }

/*
    @GetMapping("/recipe/{id}")
    public String testMethod(@PathVariable String id){
        return "Recipe with Id: "+id;
    }

    @GetMapping ("/recipe")
    public String testMethod2(@RequestParam String recipeName){
        return "Your query param is:" + recipeName;
    }

*/

}
