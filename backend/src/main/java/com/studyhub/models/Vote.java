package com.studyhub.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
@Document(collection = "votes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    @Id
    private String id;
    private String userId;
    private String targetId; //if for q/a being voted on 
    private String targetType; //question or answer
    private Integer voteType; // +1 for upvote, -1 for downvote

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
