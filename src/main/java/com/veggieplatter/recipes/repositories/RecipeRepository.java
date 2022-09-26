package com.veggieplatter.recipes.repositories;


import com.veggieplatter.recipes.entites.Recipe;
import com.veggieplatter.recipes.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Transactional
    void findAllByUserId(Long id);

    /*@Transactional
    List<Recipe> findAllByNameIn(List<Long> id);*/
}

