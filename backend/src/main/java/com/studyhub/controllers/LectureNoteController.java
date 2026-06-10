package com.studyhub.controllers;

import com.studyhub.models.LectureNote;
import com.studyhub.repositories.LectureNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lecture-notes")
public class LectureNoteController {
    
    @Autowired
    private LectureNoteRepository lectureNoteService;

    @PostMapping
    public ResponseEntity<LectureNote> createLectureNote(@RequestBody LectureNote lectureNote) {
        LectureNote createdNote = lectureNoteService.save(lectureNote);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LectureNote> getLectureNoteById(@PathVariable String id)
    {
        Optional<LectureNote> lectureNote = lectureNoteService.findById(id);
        return lectureNote.map(note -> new ResponseEntity<>(note, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<LectureNote>> getAllLectureNotes() {
        List<LectureNote> lectureNotes = lectureNoteService.findAll();
        return new ResponseEntity<>(lectureNotes, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LectureNote>> getLectureNotesByUserId(@PathVariable String
    userId) {
            List<LectureNote> lectureNotes = lectureNoteService.findByUserId(userId);
            return new ResponseEntity<>(lectureNotes, HttpStatus.OK);
        }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<LectureNote>> getLectureNotesByUsername(@PathVariable String username) {
        List<LectureNote> lectureNotes = lectureNoteService.findByUsername(username);
        return new ResponseEntity<>(lectureNotes, HttpStatus.OK);
    }

}
