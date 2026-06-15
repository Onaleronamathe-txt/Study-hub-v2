package com.studyhub.controllers;
import com.studyhub.models.CalendarEvent;

import com.studyhub.services.CalendarEventService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController

@RequestMapping("/calendar-events")

@CrossOrigin(origins = "http://localhost:3000")

public class CalendarEventController {

    

    @Autowired

    private CalendarEventService calendarEventService;

    

    @GetMapping("/{id}")

    public ResponseEntity<CalendarEvent> getEventById(@PathVariable String id) {

        Optional<CalendarEvent> event = calendarEventService.getEventById(id);

        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    

    @GetMapping("/user/{userId}")

    public ResponseEntity<List<CalendarEvent>> getEventsByUserId(@PathVariable String userId) {

        List<CalendarEvent> events = calendarEventService.getEventsByUserId(userId);

        return ResponseEntity.ok(events);

    }

    

    @GetMapping("/subject/{subjectId}")

    public ResponseEntity<List<CalendarEvent>> getEventsBySubjectId(@PathVariable String subjectId) {

        List<CalendarEvent> events = calendarEventService.getEventsBySubjectId(subjectId);

        return ResponseEntity.ok(events);

    }

    

    @PostMapping

    public ResponseEntity<CalendarEvent> createEvent(@RequestBody CalendarEvent event) {

        CalendarEvent created = calendarEventService.createEvent(event);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

    

    @PutMapping("/{id}")

    public ResponseEntity<CalendarEvent> updateEvent(@PathVariable String id, @RequestBody CalendarEvent event) {

        try {

            CalendarEvent updated = calendarEventService.updateEvent(id, event);

            return ResponseEntity.ok(updated);

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();

        }

    }

    

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {

        calendarEventService.deleteEvent(id);

        return ResponseEntity.noContent().build();

    }

}
