package com.studyhub.controllers;
import com.studyhub.models.Answer;
import com.studyhub.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("answers")
@CrossOrigin(origins = "http://localhost:3000")

public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable String id) {
        Optional<Answer> answer = answerService.getAnswerById(id);
        if(answer.isPresent()){
            return new ResponseEntity<>(answer.get(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Answer>> getAnswersByQuestionId(@PathVariable String questionId){
        List<Answer> answers = answerService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(answers);
    }
    @PostMapping("/user/{userId}")
    public ResponseEntity<List<Answer>> getAnswersByUserId(@PathVariable String userId){
        List<Answer> answers = answerService.getAnswersByUserId(userId);
        return ResponseEntity.ok(answers);
    }
    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {
        Answer createdAnswer = answerService.createAnswer(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswer);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable String id, @RequestBody Answer updatedAnswer) {
        try {
            Answer answer = answerService.updateAnswer(id, updatedAnswer);
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}/mark-correct")
    public ResponseEntity<Answer> markAnswerAsCorrect(@PathVariable String id) {
        try {
            Answer answer = answerService.markAnswerAsCorrect(id);
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}/upvote")
    public ResponseEntity<Answer> upvoteAnswer(@PathVariable String id) {
        try {
            Answer answer = answerService.upvoteAnswer(id);
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}/downvote")
    public ResponseEntity<Answer> downvoteAnswer(@PathVariable String id) {
        try {
            Answer answer = answerService.downvoteAnswer(id);
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable String id) {
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
