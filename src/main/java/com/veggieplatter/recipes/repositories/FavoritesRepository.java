package com.veggieplatter.recipes.repositories;


import com.veggieplatter.recipes.entites.Favorites;
import com.veggieplatter.recipes.entites.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    @Transactional
    List<Favorites> findAllByUserId(Long id);


}

