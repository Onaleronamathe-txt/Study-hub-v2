package com.studyhub.repositories;
import com.studyhub.models.SaveVideo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SaveVideoRepository extends MongoRepository<SaveVideo, String> {
    List<SaveVideo> findByUserId(String userId);
    List<SaveVideo> findBySubjectId(String subjectId);
    List<SaveVideo> findByUserIdAndSubjectId(String userId, String subjectId);
}
