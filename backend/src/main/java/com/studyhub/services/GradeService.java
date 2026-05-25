package com.studyhub.services;

import org.springframework.stereotype.Service;
import com.studyhub.models.Grade;
import com.studyhub.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class GradeService {
    
    @Autowired
    private GradeRepository gradeRepository;

    // Create new grade
    public Grade createGrade(Grade grade) {
        grade.setCreatedAt(LocalDateTime.now());
        grade.setUpdatedAt(LocalDateTime.now());
        grade.setPercentage((grade.getScore() / grade.getMaxScore()) * 100);
        return gradeRepository.save(grade);
    }

    // Get grade by id
    public Optional<Grade> getGradeById(String id) {
        return gradeRepository.findById(id);
    }

    // Get grades for specific subject
    public List<Grade> getGradesBySubjectId(String subjectId) {
        return gradeRepository.findBySubjectId(subjectId);
    }

    // Get grades for specific user
    public List<Grade> getGradesByUserId(String userId) {
        return gradeRepository.findByUserId(userId);
    }

    // Get grades by user and subject
    public List<Grade> getGradesByUserAndSubject(String userId, String subjectId) {
        return gradeRepository.findByUserIdAndSubjectId(userId, subjectId);
    }

    // Calculate average for user
    public Double calculateAverageForUser(String userId) {
        List<Grade> grades = getGradesByUserId(userId);
        if (grades.isEmpty()) return 0.0;
        return grades.stream()
                .mapToDouble(Grade::getPercentage)
                .average()
                .orElse(0.0);
    }

    // Calculate average for subject
    public Double calculateAverageForSubject(String userId, String subjectId) {
        List<Grade> grades = getGradesByUserAndSubject(userId, subjectId);
        if (grades.isEmpty()) return 0.0;
        return grades.stream()
                .mapToDouble(Grade::getPercentage)
                .average()
                .orElse(0.0);
    }

    // Update grade
    public Grade updateGrade(String id, Grade grade) {
        return gradeRepository.findById(id).map(existing -> {
            existing.setScore(grade.getScore());
            existing.setMaxScore(grade.getMaxScore());
            existing.setPercentage((grade.getScore() / grade.getMaxScore()) * 100);
            existing.setUpdatedAt(LocalDateTime.now());
            return gradeRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));
    }

    // Delete grade
    public void deleteGrade(String id) {
        gradeRepository.deleteById(id);
    }
}