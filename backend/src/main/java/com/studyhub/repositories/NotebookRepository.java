package com.studyhub.repositories;

import com.studyhub.models.Notebook;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotebookRepository extends MongoRepository<Notebook, String> {
    List<Notebook> findByUserId(String userId);
    List<Notebook> findByUserIdOrderByCreatedAtDesc(String userId);
}
