package com.studyhub.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
@Document(collection = "grades")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    @Id
    private String id;
    private String userId;
    private String assesmentName;
    private Double score;
    private Double maxScore;
    private Double percentage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
