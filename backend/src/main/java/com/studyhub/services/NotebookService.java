package com.studyhub.services;

import com.studyhub.models.Notebook;
import com.studyhub.repositories.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotebookService {
    
    @Autowired
    private NotebookRepository notebookRepository;
    
    public Notebook createNotebook(Notebook notebook) {
        notebook.setCreatedAt(LocalDateTime.now());
        notebook.setUpdatedAt(LocalDateTime.now());
        return notebookRepository.save(notebook);
    }
    
    public Optional<Notebook> getNotebookById(String id) {
        return notebookRepository.findById(id);
    }
    
    public List<Notebook> getNotebooksByUserId(String userId) {
        return notebookRepository.findByUserId(userId);
    }
    
    public List<Notebook> getRecentNotebooksByUserId(String userId) {
        return notebookRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
    
    public Notebook updateNotebook(String id, Notebook notebook) {
        return notebookRepository.findById(id).map(existing -> {
            existing.setTitle(notebook.getTitle());
            existing.setContent(notebook.getContent());
            existing.setTags(notebook.getTags());
            existing.setUpdatedAt(LocalDateTime.now());
            return notebookRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Notebook not found with id: " + id));
    }
    
    public void deleteNotebook(String id) {
        notebookRepository.deleteById(id);
    }
}
