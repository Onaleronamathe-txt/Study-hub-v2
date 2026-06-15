package com.studyhub.services;

import com.studyhub.models.SaveVideo;
import com.studyhub.repositories.SaveVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SavedVideoService {
    
    @Autowired
    private SaveVideoRepository saveVideoRepository;
    
    public SaveVideo createVideo(SaveVideo video) {
        video.setCreatedAt(LocalDateTime.now());
        video.setUpdatedAt(LocalDateTime.now());
        return saveVideoRepository.save(video);
    }

    public List <SaveVideo> getAllVideos() {
        return saveVideoRepository.findAll();
    }
    
    public Optional<SaveVideo> getVideoById(String id) {
        return saveVideoRepository.findById(id);
    }
    
    public List<SaveVideo> getVideosByUserId(String userId) {
        return saveVideoRepository.findByUserId(userId);
    }
    
    public List<SaveVideo> getVideosBySubjectId(String subjectId) {
        return saveVideoRepository.findBySubjectId(subjectId);
    }
    
    public SaveVideo saveVideo(SaveVideo video) {
    return saveVideoRepository.save(video);
}

    public List<SaveVideo> getVideosByUserAndSubject(String userId, String subjectId) {
        return saveVideoRepository.findByUserIdAndSubjectId(userId, subjectId);
    }
    
    public SaveVideo updateVideo(String id, SaveVideo video) {
        return saveVideoRepository.findById(id).map(existing -> {
            existing.setTitle(video.getTitle());
            existing.setDescription(video.getDescription());
            existing.setVideoUrl(video.getVideoUrl());
            return saveVideoRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Video not found with id: " + id));
    }
    
    public void deleteVideo(String id) {
        saveVideoRepository.deleteById(id);
    }
}