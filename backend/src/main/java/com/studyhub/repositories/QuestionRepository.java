package com.studyhub.repositories;

import com.studyhub.models.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findByUserId(String userId);
    List<Question> findBySubject(String subject);
    List<Question> findAllByOrderByVotesDesc();
    List<Question> findAllByOrderByCreatedAtDesc();
}
