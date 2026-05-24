package com.studyhub.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
@Document(collection = "calendarevents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarEvent {
    @Id
    private String id;
    private String userId;
    private String title;
    private String description;
    private String subjectId;
    private String type; //"assignment", "test" ,"exam"
    private LocalDateTime eventDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
