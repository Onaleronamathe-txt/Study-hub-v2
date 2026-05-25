package com.studyhub.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
@Document(collection = "subjects")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Subject {
    @Id
    private String id;
    private String userId;
    private String name;
    private String description;
    private String color;
    private String icon;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
