package com.studyhub.services;

import com.studyhub.models.Subject;
import com.studyhub.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired

    private SubjectRepository subjectRepository;
    // Create a new subject
    public Subject createSubject(Subject subject) {
        subject.setCreatedAt(LocalDateTime.now());
        subject.setUpdatedAt(LocalDateTime.now());
        return subjectRepository.save(subject);
    }

    // Get subject by ID
    public Optional<Subject> getSubjectById(String id) {
        return subjectRepository.findById(id);
    }
    //get all subjects for user
    public List<Subject> getSubjectsByUserId(String userId) {
        return subjectRepository.findByUserId(userId);
    }
    //update subject 
    public Subject updateSubject(String id, Subject subject) {
        return subjectRepository.findById(id).map(existing -> {
            existing.setName(subject.getName());
            existing.setDescription(subject.getDescription());
            existing.setUpdatedAt(LocalDateTime.now());
            existing.setColor(subject.getColor());
            return subjectRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Subject not found with id " + id));
    }

    //delete subject by id 
    public void deleteSubject(String id) {
        subjectRepository.deleteById(id);
    }

}
