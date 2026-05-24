package com.studyhub.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "savevideos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveVideo {
    @Id
    private String id;
    private String userId;
    private String subjectId;
    private String title;
    private String videoUrl;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
