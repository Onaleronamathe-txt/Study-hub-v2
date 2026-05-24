package com.studyhub.repositories;

import com.studyhub.models.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface AnswerRepository extends MongoRepository<Answer, String> {
    List<Answer> findByQuestionId(String questionId);
    List<Answer> findByUserId(String userId);
    List<Answer> findByQuestionIdOrderByVotesDesc(String questionId);
    List<Answer> findAllByOrderByCreatedAtDesc();
}

