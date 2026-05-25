package com.studyhub.repositories;

import com.studyhub.models.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface SubjectRepository extends MongoRepository<Subject, String> {
    List<Subject> findByUserId(String userId);
}
