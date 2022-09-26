package com.veggieplatter.recipes.controller;

import com.veggieplatter.recipes.dtos.FavoritesDto;
import com.veggieplatter.recipes.dtos.RecipeDto;
import com.veggieplatter.recipes.services.FavoriteService;
import com.veggieplatter.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {
    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/like")
    public void favoriteRecipe(@RequestBody FavoritesDto favoritesDto){

        //System.err.println("UserID: -- "+ favoritesDto.getUserId() + " -- Recipe ID: -- " + favoritesDto.getRecipeId());
        favoriteService.favoriteRecipe(favoritesDto);
    }

    @GetMapping("/{userId}")
    public List<RecipeDto> allFavoriteRecipe(@PathVariable Long userId){

        //System.err.println("UserID: -- "+ favoritesDto.getUserId() + " -- Recipe ID: -- " + favoritesDto.getRecipeId());
        return favoriteService.getAllFavorites(userId);
    }

}