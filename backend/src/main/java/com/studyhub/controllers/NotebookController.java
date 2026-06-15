
package com.studyhub.controllers;

import com.studyhub.models.Notebook;

import com.studyhub.services.NotebookService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController

@RequestMapping("/notebooks")

@CrossOrigin(origins = "http://localhost:3000")

public class NotebookController {

    

    @Autowired

    private NotebookService notebookService;

    

    @GetMapping("/{id}")

    public ResponseEntity<Notebook> getNotebookById(@PathVariable String id) {

        Optional<Notebook> notebook = notebookService.getNotebookById(id);

        return notebook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    

    @GetMapping("/user/{userId}")

    public ResponseEntity<List<Notebook>> getNotebooksByUserId(@PathVariable String userId) {

        List<Notebook> notebooks = notebookService.getNotebooksByUserId(userId);

        return ResponseEntity.ok(notebooks);

    }

    

    @PostMapping

    public ResponseEntity<Notebook> createNotebook(@RequestBody Notebook notebook) {

        Notebook created = notebookService.createNotebook(notebook);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

    

    @PutMapping("/{id}")

    public ResponseEntity<Notebook> updateNotebook(@PathVariable String id, @RequestBody Notebook notebook) {

        try {

            Notebook updated = notebookService.updateNotebook(id, notebook);

            return ResponseEntity.ok(updated);

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();

        }

    }

    

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteNotebook(@PathVariable String id) {

        notebookService.deleteNotebook(id);

        return ResponseEntity.noContent().build();

    }


}
