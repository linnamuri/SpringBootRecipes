package com.veggieplatter.recipes.services;

import com.veggieplatter.recipes.dtos.FavoritesDto;
import com.veggieplatter.recipes.dtos.RecipeDto;
import com.veggieplatter.recipes.entites.Favorites;
import com.veggieplatter.recipes.entites.Recipe;
import com.veggieplatter.recipes.entites.User;
import com.veggieplatter.recipes.repositories.FavoritesRepository;
import com.veggieplatter.recipes.repositories.RecipeRepository;
import com.veggieplatter.recipes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoritesServiceImpl implements FavoriteService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    FavoritesRepository favoritesRepository;



    @Override
    @Transactional
    public void favoriteRecipe(FavoritesDto favoritesDto) {
        Optional<User> userOptional = userRepository.findById(favoritesDto.getUserId());
        Optional<Recipe> userOptionalRecipe = recipeRepository.findById(favoritesDto.getRecipeId());
        Favorites favorites = new Favorites(favoritesDto);

        userOptional.ifPresent(favorites::setUser);
        userOptionalRecipe.ifPresent(favorites::setRecipe);

        //System.err.println("UserID: -- "+ favorites.getUserId() + " -- Recipe ID: -- " + favorites.getRecipeId());
        favoritesRepository.saveAndFlush(favorites);

    }
    @Override
    public List<RecipeDto> getAllFavorites(Long userId){
        List<Favorites> favoritesList = favoritesRepository.findAllByUserId(userId);
        List<RecipeDto> recipesDtoList=new ArrayList<RecipeDto>();
        System.err.println(favoritesList);

        Iterator<Favorites> it = favoritesList.iterator();
        while (it.hasNext()) {

            // Print all elements of List
            Favorites favorites = it.next();
            /*FavoritesDto favoritesDto = new FavoritesDto();
            favoritesDto.setId(favorites.getId());
            favoritesDto.setUserId(favorites.getUser().getId());
            favoritesDto.setRecipeId(favorites.getRecipe().getId());*/
            recipesDtoList.add(new RecipeDto(favorites.getRecipe()));


        }
        return recipesDtoList;
        //return favoritesList.stream().map(favorite -> new FavoritesDto(favorite)).collect(Collectors.toList());
        //return null;
        //return noteList.stream().map(note -> new NoteDto(note)).collect(Collectors.toList());
        //}
        //return Collections.emptyList();
    }


}
