package com.studyhub.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.studyhub.models.Grade;
import java.util.List;


public interface GradeRepository extends MongoRepository<Grade, String> {
    List<Grade> findByUserId(String userId);
    List<Grade> findBySubjectId(String subjectId);
    List<Grade> findByUserIdAndSubjectId(String userId, String subjectId);
}
