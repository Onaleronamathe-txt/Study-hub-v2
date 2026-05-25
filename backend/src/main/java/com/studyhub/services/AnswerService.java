package com.studyhub.services;

import org.springframework.stereotype.Service;
import com.studyhub.models.Answer;
import com.studyhub.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
@Service

public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
//create new answer
    public Answer createAnswer(Answer answer) {
        answer.setCreatedAt(LocalDateTime.now());
        answer.setUpdatedAt(LocalDateTime.now());
        answer.setVotes(0);
        answer.setIsCorrect(false);
        return answerRepository.save(answer);
    }
//get answer by id
    public Optional<Answer> getAnswerById(String id) {
        return answerRepository.findById(id);
    }
    //get answers for a question 
    public List<Answer> getAnswersByQuestionId(String questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    //get answers by user
    public List<Answer> getAnswersByUserId(String userId) {
        return answerRepository.findByUserId(userId);
    }

    //update answer
    public Answer updateAnswer(String id, Answer answer) {
        return answerRepository.findById(id).map(existing -> {
            existing.setContent(answer.getContent());
            existing.setUpdatedAt(LocalDateTime.now());
            return answerRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Answer not found with id " + id));
    }

    //answer correct 
    public Answer markAnswerAsCorrect(String id) {
        return answerRepository.findById(id).map(answer -> {
            answer.setIsCorrect(true);
            return answerRepository.save(answer);
        }).orElseThrow(() -> new RuntimeException("Answer not found with id " + id));
    }

    //upvote answer
    public Answer upvoteAnswer(String id) {
        return answerRepository.findById(id).map(answer -> {
            answer.setVotes(answer.getVotes() + 1);
            return answerRepository.save(answer);
        }).orElseThrow(() -> new RuntimeException("Answer not found with id " + id));
    }

    //downvote answer
    public Answer downvoteAnswer(String id) {
        return answerRepository.findById(id).map(answer -> {
            answer.setVotes(answer.getVotes() - 1);
            return answerRepository.save(answer);
        }).orElseThrow(() -> new RuntimeException("Answer not found with id " + id));
    }

    //delete answer
    public void deleteAnswer(String id) {
        answerRepository.deleteById(id);
    }

}
