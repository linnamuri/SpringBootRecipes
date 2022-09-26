package com.veggieplatter.recipes.services;

import com.veggieplatter.recipes.dtos.FavoritesDto;
import com.veggieplatter.recipes.dtos.RecipeDto;
import com.veggieplatter.recipes.dtos.UserDto;

import javax.transaction.Transactional;
import java.util.List;

public interface FavoriteService {
    @Transactional
    void favoriteRecipe(FavoritesDto favoritesDto);


    List<RecipeDto> getAllFavorites(Long userId);

}
