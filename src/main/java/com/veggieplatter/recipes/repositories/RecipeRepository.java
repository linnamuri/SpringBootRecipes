package com.veggieplatter.recipes.repositories;


import com.veggieplatter.recipes.entites.Recipe;
import com.veggieplatter.recipes.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAll();
}

