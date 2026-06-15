package com.studyhub.services;

import com.studyhub.models.Vote;
import com.studyhub.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VoteServices {
    
    @Autowired
    private VoteRepository voteRepository;
    
    // Create vote
    public Vote createVote(Vote vote) {
        vote.setCreatedAt(LocalDateTime.now());
        return voteRepository.save(vote);
    }
    
    // Get vote by ID
    public Optional<Vote> getVoteById(String id) {
        return voteRepository.findById(id);
    }
    
    // Get votes for a question
    public List<Vote> getVotesByQuestionId(String questionId) {
        return voteRepository.findByQuestionId(questionId);
    }
    
    // Get votes for an answer
    public List<Vote> getVotesByAnswerId(String answerId) {
        return voteRepository.findByAnswerId(answerId);
    }
    
    // Get votes by user
    public List<Vote> getVotesByUserId(String userId) {
        return voteRepository.findByUserId(userId);
    }
    
    // Check if user already voted
    public Optional<Vote> getUserVoteForQuestion(String userId, String questionId) {
        return voteRepository.findByUserIdAndQuestionId(userId, questionId);
    }
    
    public Optional<Vote> getUserVoteForAnswer(String userId, String answerId) {
        return voteRepository.findByUserIdAndAnswerId(userId, answerId);
    }
    
    // Delete vote
    public void deleteVote(String id) {
        voteRepository.deleteById(id);
    }
}
