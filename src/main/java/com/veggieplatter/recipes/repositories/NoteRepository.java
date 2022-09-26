package com.veggieplatter.recipes.repositories;


import com.veggieplatter.recipes.entites.Note;
import com.veggieplatter.recipes.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUserEquals(User user);
}

