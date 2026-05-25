package com.studyhub.services;

import com.studyhub.models.Question;
import com.studyhub.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    // Create a new question
    public Question createQuestion(Question question) {
        question.setCreatedAt(LocalDateTime.now());
        question.setUpdatedAt(LocalDateTime.now());
        question.setVotes(0);
        question.setViewCount(0);
        return questionRepository.save(question);
    }
    
    // Get question by ID
    public Optional<Question> getQuestionById(String id) {
        return questionRepository.findById(id);
    }
    
    // Get all questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    
    // Get questions by user
    public List<Question> getQuestionsByUserId(String userId) {
        return questionRepository.findByUserId(userId);
    }
    
    // Get questions by subject
    public List<Question> getQuestionsBySubject(String subject) {
        return questionRepository.findBySubject(subject);
    }
    
    // Get trending questions (sorted by votes)
    public List<Question> getTrendingQuestions() {
        return questionRepository.findAllByOrderByVotesDesc();
    }
    
    // Get recent questions (sorted by date)
    public List<Question> getRecentQuestions() {
        return questionRepository.findAllByOrderByCreatedAtDesc();
    }
    
    // Update question
    public Question updateQuestion(String id, Question question) {
        return questionRepository.findById(id).map(existing -> {
            existing.setTitle(question.getTitle());
            existing.setContent(question.getContent());
            existing.setTags(question.getTags());
            existing.setUpdatedAt(LocalDateTime.now());
            return questionRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
    }
    
    // Increment view count
    public Question incrementViewCount(String id) {
        return questionRepository.findById(id).map(question -> {
        question.setViewCount(question.getViewCount() + 1);
        return questionRepository.save(question);
        }).orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
    }
    
    // Upvote question
    public Question upvoteQuestion(String id) {
        return questionRepository.findById(id).map(question -> {
        question.setVotes(question.getVotes() + 1);
        return questionRepository.save(question);
        }).orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
    }
    
    // Downvote question
    public Question downvoteQuestion(String id) {
        return questionRepository.findById(id).map(question -> {
        question.setVotes(question.getVotes() - 1);
        return questionRepository.save(question);
        }).orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
    }
    
    // Delete question
    public void deleteQuestion(String id) {
        questionRepository.deleteById(id);
    }
}