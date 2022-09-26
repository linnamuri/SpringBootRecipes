package com.veggieplatter.recipes.dtos;

import com.veggieplatter.recipes.entites.Favorites;
import com.veggieplatter.recipes.entites.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritesDto {

    private Long id;

    private Long userId;

    private Long recipeId;

    public FavoritesDto(Favorites favorites){
        if (favorites.getId() != null){
            this.id = favorites.getId();
        }
        if (favorites.getUserId() != null){
            this.userId = favorites.getUserId();
        }
        if (favorites.getRecipeId() != null){
            this.userId = favorites.getRecipeId();
        }
    }
}
