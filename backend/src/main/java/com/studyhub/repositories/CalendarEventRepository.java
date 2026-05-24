package com.studyhub.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.studyhub.models.CalendarEvent;
import java.util.List;
import java.time.LocalDateTime;


public interface CalendarEventRepository extends MongoRepository<CalendarEvent, String> {
    List<CalendarEvent> findByUserId(String userId);
    List<CalendarEvent> findBySubjectId(String subjectId);
    List<CalendarEvent> findByUserIdAndDateBetween(LocalDateTime start, LocalDateTime end);
}
