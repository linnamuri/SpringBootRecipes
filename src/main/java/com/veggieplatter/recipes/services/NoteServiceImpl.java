
package com.veggieplatter.recipes.services;

import com.veggieplatter.recipes.dtos.NoteDto;
import com.veggieplatter.recipes.entites.Note;
import com.veggieplatter.recipes.entites.User;
import com.veggieplatter.recipes.repositories.NoteRepository;
import com.veggieplatter.recipes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteServiceI {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<NoteDto> getAllNotesByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Note> noteList = noteRepository.findAllByUserEquals(userOptional.get());
            return noteList.stream().map(note -> new NoteDto(note)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    @Override
    @Transactional
    public void addNote(NoteDto noteDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Note note = new Note(noteDto);
        userOptional.ifPresent(note::setUser);
        noteRepository.saveAndFlush(note);
    }


    @Override
    @Transactional
    public void deleteNoteById(Long noteId) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);
        noteOptional.ifPresent(note -> noteRepository.delete(note));
    }


    @Override
    @Transactional
    public void updateNoteById(NoteDto noteDto) {
        Optional<Note> noteOptional = noteRepository.findById(noteDto.getId());
        noteOptional.ifPresent(note -> {
            note.setBody(noteDto.getBody());
            noteRepository.saveAndFlush(note);
        });
    }

    @Override
    public Optional<NoteDto> getNoteById(Long noteId) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);
        if (noteOptional.isPresent()){
            return Optional.of(new NoteDto(noteOptional.get()));
        }
        return Optional.empty();
    }
}

