package com.studyhub.repositories;

import com.studyhub.models.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;


public interface VoteRepository extends MongoRepository<Vote, String> {
    List<Vote> findByUserId(String userId);
    List<Vote> findByTargetId(String targetId);
    List<Vote> findByTargetIdAndType(String targetId, String type);
    Optional<Vote> findByUserIdAndTargetId(String userId, String subjectId);
    List<Vote> findByQuestionId(String questionId);
    List<Vote> findByAnswerId(String answerId);
    Optional<Vote> findByUserIdAndQuestionId(String userId, String questionId);
    Optional<Vote> findByUserIdAndAnswerId(String userId, String answerId);
}
