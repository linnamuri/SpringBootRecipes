package com.veggieplatter.recipes.services;

import com.veggieplatter.recipes.dtos.RecipeDto;
import com.veggieplatter.recipes.entites.Recipe;
import com.veggieplatter.recipes.entites.User;
import com.veggieplatter.recipes.repositories.RecipeRepository;
import com.veggieplatter.recipes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public List<RecipeDto> getAllRecipes(){
        //Optional<User> userOptional = userRepository.findById(userId);
        //if (userOptional.isPresent()){
            List<Recipe> recipeList = recipeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            return recipeList.stream().map(recipe -> new RecipeDto(recipe)).collect(Collectors.toList());
            //return null;
            //return noteList.stream().map(note -> new NoteDto(note)).collect(Collectors.toList());
        //}
        //return Collections.emptyList();
    }

    @Override
    @Transactional
    public List<RecipeDto> addRecipe(RecipeDto recipeDto, Long userId) {
        recipeDto.setUserId(userId);
        Optional<User> userOptional = userRepository.findById(userId);
        Recipe recipeNew = new Recipe(recipeDto);
        userOptional.ifPresent(recipeNew::setUser);
        recipeRepository.saveAndFlush(recipeNew);
        List<Recipe> recipeList = recipeRepository.findAll();
        return recipeList.stream().map(recipe -> new RecipeDto(recipe)).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void deleteRecipeById(Long Id){
        recipeRepository.deleteById(Id);
        System.out.println("control is here");
    }

    @Override
    @Transactional
    public void updateRecipeById(RecipeDto recipeDto) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeDto.getId());
        recipeOptional.ifPresent(recipe -> {
            recipe.setRecipeName(recipeDto.getRecipeName());
            recipeRepository.saveAndFlush(recipe);
        });
    }

}
