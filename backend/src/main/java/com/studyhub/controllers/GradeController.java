package com.studyhub.controllers;

import com.studyhub.models.Grade;
import com.studyhub.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("grades")
@CrossOrigin(origins = "http://localhost:3000")

public class GradeController {
    @Autowired
    private GradeService gradeService;
    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable String id) {
        Optional<Grade> grade = gradeService.getGradeById(id);
        if(grade.isPresent()){
            return new ResponseEntity<>(grade.get(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Grade>> getGradesByUserId(@PathVariable String userId) {
        List<Grade> grades = gradeService.getGradesByUserId(userId);
        return ResponseEntity.ok(grades);
    }
   @GetMapping("/subject/{subjectId}")

    public ResponseEntity<List<Grade>> getGradesBySubjectId(@PathVariable String subjectId) {
        List<Grade> grades = gradeService.getGradesBySubjectId(subjectId);
        return ResponseEntity.ok(grades);
    }
    @GetMapping("/user/{userId}/subject/{subjectId}")

    public ResponseEntity<List<Grade>> getGradesByUserAndSubject(
            @PathVariable String userId, 
            @PathVariable String subjectId) {
        List<Grade> grades = gradeService.getGradesByUserAndSubject(userId, subjectId);
        return ResponseEntity.ok(grades);

    }
    @GetMapping("/average/user/{userId}")

    public ResponseEntity<Map<String, Double>> getAverageForUser(@PathVariable String userId) {
        Double average = gradeService.calculateAverageForUser(userId);
        Map<String, Double> response = new HashMap<>();
        response.put("average", average);
        return ResponseEntity.ok(response);
    }
     @GetMapping("/average/user/{userId}/subject/{subjectId}")

    public ResponseEntity<Map<String, Double>> getAverageForSubject(
            @PathVariable String userId, 
            @PathVariable String subjectId) {
        Double average = gradeService.calculateAverageForSubject(userId, subjectId);
        Map<String, Double> response = new HashMap<>();
        response.put("average", average);
        return ResponseEntity.ok(response);
    }
 @PostMapping
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
        Grade createdGrade = gradeService.createGrade(grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGrade);
    }

     @PutMapping("/{id}")

    public ResponseEntity<Grade> updateGrade(@PathVariable String id, @RequestBody Grade grade) {

        try {

            Grade updatedGrade = gradeService.updateGrade(id, grade);

            return ResponseEntity.ok(updatedGrade);

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable String id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }


}
