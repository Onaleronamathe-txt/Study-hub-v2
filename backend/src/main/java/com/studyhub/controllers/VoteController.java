package com.studyhub.controllers;

import com.studyhub.models.Vote;
import com.studyhub.services.VoteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteServices voteServices;

    // Create a new vote
    @PostMapping
    public ResponseEntity<Vote> createVote(@RequestBody Vote vote) {
        Vote createdVote = voteServices.createVote(vote);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVote);
    }

    // Get vote by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVoteById(@PathVariable String id) {
        Optional<Vote> vote = voteServices.getVoteById(id);
        return vote.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all votes for a question
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Vote>> getVotesByQuestionId(@PathVariable String questionId) {
        List<Vote> votes = voteServices.getVotesByQuestionId(questionId);
        return ResponseEntity.ok(votes);
    }

    // Get all votes for an answer
    @GetMapping("/answer/{answerId}")
    public ResponseEntity<List<Vote>> getVotesByAnswerId(@PathVariable String answerId) {
        List<Vote> votes = voteServices.getVotesByAnswerId(answerId);
        return ResponseEntity.ok(votes);
    }

    // Get votes by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Vote>> getVotesByUserId(@PathVariable String userId) {
        List<Vote> votes = voteServices.getVotesByUserId(userId);
        return ResponseEntity.ok(votes);
    }

    // Check if user already voted for a question
    @GetMapping("/user/{userId}/question/{questionId}")
    public ResponseEntity<Vote> getUserVoteForQuestion(@PathVariable String userId, @PathVariable String questionId) {
        Optional<Vote> vote = voteServices.getUserVoteForQuestion(userId, questionId);
        return vote.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Check if user already voted for an answer
    @GetMapping("/user/{userId}/answer/{answerId}")
    public ResponseEntity<Vote> getUserVoteForAnswer(@PathVariable String userId, @PathVariable String answerId) {
        Optional<Vote> vote = voteServices.getUserVoteForAnswer(userId, answerId);
        return vote.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a vote
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable String id) {
        Optional<Vote> vote = voteServices.getVoteById(id);
        if (vote.isPresent()) {
            voteServices.deleteVote(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}