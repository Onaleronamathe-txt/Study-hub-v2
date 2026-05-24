package com.studyhub.repositories;

import com.studyhub.models.LectureNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface LectureNoteRepository extends MongoRepository<LectureNote, String> {
    List<LectureNote> findByUserId(String userId);
    List<LectureNote> findByUserIdAndSubjectId( String userId, String subjectId
    );
    List<LectureNote> findBySubject(String subjectId);
}
