package com.studyhub.controllers;

import com.studyhub.models.SaveVideo;

import com.studyhub.services.SavedVideoService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController

@RequestMapping("/saved-videos")

@CrossOrigin(origins = "http://localhost:3000")

public class SavedVideoController {

    

    @Autowired

    private SavedVideoService savedVideoService;

    

    @GetMapping("/{id}")

    public ResponseEntity<SaveVideo> getVideoById(@PathVariable String id) {

        Optional<SaveVideo> video = savedVideoService.getVideoById(id);

        return video.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    

    @GetMapping("/user/{userId}")

    public ResponseEntity<List<SaveVideo>> getVideosByUserId(@PathVariable String userId) {

        List<SaveVideo> videos = savedVideoService.getVideosByUserId(userId);

        return ResponseEntity.ok(videos);

    }

    

    @GetMapping("/subject/{subjectId}")

    public ResponseEntity<List<SaveVideo>> getVideosBySubjectId(@PathVariable String subjectId) {

        List<SaveVideo> videos = savedVideoService.getVideosBySubjectId(subjectId);

        return ResponseEntity.ok(videos);

    }

    

    @PostMapping

    public ResponseEntity<SaveVideo> saveVideo(@RequestBody SaveVideo video) {

        SaveVideo saved = savedVideoService.saveVideo(video);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }

    

    @PutMapping("/{id}")

    public ResponseEntity<SaveVideo> updateVideo(@PathVariable String id, @RequestBody SaveVideo video) {

        try {

            SaveVideo updated = savedVideoService.updateVideo(id, video);

            return ResponseEntity.ok(updated);

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();

        }

    }

    

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteVideo(@PathVariable String id) {

        savedVideoService.deleteVideo(id);

        return ResponseEntity.noContent().build();

    }

}

