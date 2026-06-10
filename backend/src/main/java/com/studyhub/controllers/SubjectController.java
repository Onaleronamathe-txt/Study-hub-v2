package com.studyhub.controllers;

import com.studyhub.models.Subject;
import com.studyhub.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("subjects")
@CrossOrigin(origins = "http://localhost:3000")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    
     @GetMapping("/{id}")

    public ResponseEntity<Subject> getSubjectById(@PathVariable String id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        return subject.map(ResponseEntity::ok) .
        orElseGet(() -> ResponseEntity.notFound().build());

    }
     @GetMapping("/user/{userId}")

    public ResponseEntity<List<Subject>> getSubjectsByUserId(@PathVariable String userId) {

        List<Subject> subjects = subjectService.getSubjectsByUserId(userId);

        return ResponseEntity.ok(subjects);

    }
     @PostMapping

    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {

        Subject createdSubject = subjectService.createSubject(subject);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);

    }
@PutMapping("/{id}")

    public ResponseEntity<Subject> updateSubject(@PathVariable String id, @RequestBody Subject subject) {

        try {

            Subject updatedSubject = subjectService.updateSubject(id, subject);

            return ResponseEntity.ok(updatedSubject);   

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();

        }

    }
@DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteSubject(@PathVariable String id) {

        subjectService.deleteSubject(id);

        return ResponseEntity.noContent().build();

    }


}

