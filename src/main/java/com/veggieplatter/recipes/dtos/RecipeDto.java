package com.veggieplatter.recipes.dtos;

import com.veggieplatter.recipes.entites.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto implements Serializable {
    private Long id;

    private String recipeName;

    private String recipeIngredients;

    private String recipeImageURL;

    private String recipeDescription;

    private Long userId;

    public RecipeDto(Recipe recipe){
        if (recipe.getId() != null){
            this.id = recipe.getId();
        }
        if (recipe.getRecipeName() != null){
            this.recipeName = recipe.getRecipeName();
        }
        if (recipe.getRecipeIngredients() != null){
            this.recipeIngredients = recipe.getRecipeIngredients();
        }
        if (recipe.getRecipeImageURL() != null){
            this.recipeImageURL = recipe.getRecipeImageURL();
        }
        if (recipe.getRecipeDescription() != null){
            this.recipeDescription = recipe.getRecipeDescription();
        }
        if (recipe.getUserId() != null){
            this.userId = recipe.getUserId();
        }
    }
}
