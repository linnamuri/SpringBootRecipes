package com.veggieplatter.recipes.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.veggieplatter.recipes.dtos.FavoritesDto;
import com.veggieplatter.recipes.dtos.RecipeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Favorites")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "recipe_id", insertable = false, updatable = false)
    private Long recipeId;


    //A User has Recipes. In this case User is the one side and Recipes is the many side. There will be a column UserId in the table recipes.
    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    @JsonBackReference
    private Recipe recipe;

    public Favorites(FavoritesDto favoritesDto) {

        if (favoritesDto.getUserId() != null) {
            this.userId = favoritesDto.getUserId();
        }
        if (favoritesDto.getRecipeId() != null) {
            this.recipeId = favoritesDto.getRecipeId();
        }
    }


    /*@Override
    public String toString() {
        return "Person [id=" + id + ", recipeName=" + recipeName + ", recipeImageURL=" + recipeImageURL + ", recipeIngredients=" + recipeIngredients + ", recipeDescription="+recipeDescription +"]";
    }*/
}

//ALTER TABLE favorites ADD CONSTRAINT uq_favorites UNIQUE(recipe_id, user_id);
