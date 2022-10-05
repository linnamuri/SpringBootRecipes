package com.veggieplatter.recipes.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.veggieplatter.recipes.dtos.RecipeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Recipes")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String recipeName;

    @Column(length = 2000)
    private String recipeIngredients;

    @Column(length = 512)
    private String recipeImageURL;

    @Column(length = 2000)
    private String recipeDescription;


    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;


    //A User has Recipes. In this case User is the ONE side and Recipes is the MANY side.
    //There will be a column UserId in the table recipes.
    @ManyToOne
    @JsonBackReference
    private User user;

   /* @ManyToOne
    @JsonBackReference
    private Favorites favorites;*/

    public Recipe(RecipeDto recipeDto) {
        if (recipeDto.getRecipeName() != null){
            this.recipeName = recipeDto.getRecipeName();
        }
        if (recipeDto.getRecipeDescription() != null){
            this.recipeDescription = recipeDto.getRecipeDescription();
        }
        if (recipeDto.getRecipeImageURL() != null){
            this.recipeImageURL = recipeDto.getRecipeImageURL();
        }
        if (recipeDto.getRecipeIngredients() != null){
            this.recipeIngredients = recipeDto.getRecipeIngredients();
        }
        if (recipeDto.getUserId() != null) {
            this.userId = recipeDto.getUserId();
        }
    }



    /*@Override
    public String toString() {
        return "Person [id=" + id + ", recipeName=" + recipeName + ", recipeImageURL=" + recipeImageURL + ", recipeIngredients=" + recipeIngredients + ", recipeDescription="+recipeDescription +"]";
    }*/
}