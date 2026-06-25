package com.studyhub.services;

import com.studyhub.models.LectureNote;
import com.studyhub.repositories.LectureNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LectureNoteService {

    @Autowired
    private LectureNoteRepository lectureNoteRepository;

    // Create a new lecture note
    public LectureNote createLectureNote(LectureNote lectureNote) {
        lectureNote.setCreatedAt(LocalDateTime.now());
        lectureNote.setUpdatedAt(LocalDateTime.now());
        return lectureNoteRepository.save(lectureNote);
    }

    // Get lecture note by ID
    public Optional<LectureNote> getLectureNoteById(String id) {
        return lectureNoteRepository.findById(id);
    }

    // Get all lecture notes
    public List<LectureNote> getAllLectureNotes() {
        return lectureNoteRepository.findAll();
    }

    // Get lecture notes by user ID
    public List<LectureNote> getLectureNotesByUserId(String userId) {
        return lectureNoteRepository.findByUserId(userId);
    }

    // Update lecture note
    public LectureNote updateLectureNote(String id, LectureNote updatedNote) {
        updatedNote.setId(id);
        updatedNote.setUpdatedAt(LocalDateTime.now());
        return lectureNoteRepository.save(updatedNote);
    }

    // Delete lecture note
    public void deleteLectureNote(String id) {
        lectureNoteRepository.deleteById(id);
    }
}