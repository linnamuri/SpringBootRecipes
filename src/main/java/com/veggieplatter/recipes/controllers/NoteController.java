package com.veggieplatter.recipes.controllers;

import com.veggieplatter.recipes.dtos.NoteDto;
import com.veggieplatter.recipes.services.NoteServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/notes")
public class NoteController {
    @Autowired
    private NoteServiceI noteService;

    @GetMapping("/user/{userId}")
    public List<NoteDto> getNotesByUser(@PathVariable Long userId){
        return noteService.getAllNotesByUserId(userId);
    }

    @GetMapping("/{noteId}")
    public Optional<NoteDto> getNoteById(@PathVariable Long noteId){
        return noteService.getNoteById(noteId);
    }

    @PostMapping("/user/{userId}")
    public void addNote(@RequestBody NoteDto noteDto,@PathVariable Long userId){
        noteService.addNote(noteDto, userId);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNoteById(@PathVariable Long noteId){
        noteService.deleteNoteById(noteId);
    }

    @PutMapping
    public void updateNote(@RequestBody NoteDto noteDto){
        noteService.updateNoteById(noteDto);
    }
}


