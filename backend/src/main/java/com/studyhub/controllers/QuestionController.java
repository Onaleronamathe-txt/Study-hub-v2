package com.studyhub.controllers;
import com.studyhub.models.Question;
import com.studyhub.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("questions")
@CrossOrigin(origins = "http://localhost:3000")

public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable String id) {
        Optional<Question> question = questionService.getQuestionById(id);
        if(question.isPresent()){
            questionService.incrementViewCount(id);
            return new ResponseEntity<>(question.get(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Question>> getQuestionsByUserId(@PathVariable String userId){
        List<Question> questions = questionService.getQuestionsByUserId(userId);
        return ResponseEntity.ok(questions);
    }
    @PostMapping("/subject/{subject}")
    public ResponseEntity<List<Question>> getQuestionsBySubject(@PathVariable String subject){
        List<Question> questions = questionService.getQuestionsBySubject(subject);
        return ResponseEntity.ok(questions);
    }
    @GetMapping("/trending")
    public ResponseEntity<List<Question>> getTrendingQuestions(){
        List<Question> questions = questionService.getTrendingQuestions();
        return ResponseEntity.ok(questions);
    }
    @PostMapping("/recent")
    public ResponseEntity<List<Question>> getRecentQuestions(){
        List<Question> questions = questionService.getRecentQuestions();
        return ResponseEntity.ok(questions);
    }@PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.createQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable String id, @RequestBody Question updatedQuestion) {
        try {
            Question question = questionService.updateQuestion(id, updatedQuestion);
            return new ResponseEntity<>(question, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}/upvote")
    public ResponseEntity<Question> upvoteQuestion(@PathVariable String id) {
        try {
            Question question = questionService.upvoteQuestion(id);
            return new ResponseEntity<>(question, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}/downvote")
    public ResponseEntity<Question> downvoteQuestion(@PathVariable String id) {
        try {
            Question question = questionService.downvoteQuestion(id);
            return new ResponseEntity<>(question, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
