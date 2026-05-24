package com.studyhub.repositories;

import com.studyhub.models.subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface SubjectRepository extends MongoRepository<subject, String> {
    List<subject> findByUserId(String userId);
}
