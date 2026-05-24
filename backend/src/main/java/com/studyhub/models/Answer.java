package com.studyhub.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
@Document(collection = "answers")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Answer {
    @Id
    private String id;
    private String content;
    private String userId;
    private String questionId;

    private Integer votes = 0;
    private Boolean isCorrect = false;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
