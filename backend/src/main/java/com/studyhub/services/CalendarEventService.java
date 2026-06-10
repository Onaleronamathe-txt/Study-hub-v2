package com.studyhub.services;

import com.studyhub.models.CalendarEvent;
import com.studyhub.repositories.CalendarEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarEventService {
    
    @Autowired
    private CalendarEventRepository calendarEventRepository;
    
    public CalendarEvent createEvent(CalendarEvent event) {
        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());
        return calendarEventRepository.save(event);
    }
    
    public Optional<CalendarEvent> getEventById(String id) {
        return calendarEventRepository.findById(id);
    }
    
    public List<CalendarEvent> getEventsByUserId(String userId) {
        return calendarEventRepository.findByUserId(userId);
    }
    
    public List<CalendarEvent> getEventsBySubjectId(String subjectId) {
        return calendarEventRepository.findBySubjectId(subjectId);
    }
    
    public CalendarEvent updateEvent(String id, CalendarEvent event) {
        return calendarEventRepository.findById(id).map(existing -> {
            existing.setTitle(event.getTitle());
            existing.setDescription(event.getDescription());
            existing.setType(event.getType());
            existing.setUpdatedAt(event.getUpdatedAt());
            return calendarEventRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }
    
    public void deleteEvent(String id) {
        calendarEventRepository.deleteById(id);
    }
}