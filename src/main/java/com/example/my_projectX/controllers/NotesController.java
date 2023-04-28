package com.example.my_projectX.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.my_projectX.dto.CreateNoteDto;
import com.example.my_projectX.dto.CreateNoteResponseDto;
import com.example.my_projectX.dto.ErrorResponseDto;
import com.example.my_projectX.entities.NoteEntity;
import com.example.my_projectX.service.NoteService;

@RestController
@RequestMapping("/tasks/{taskId}/notes")

public class NotesController {

    private NoteService noteService;

    public NotesController (NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId) {
        var notes =  noteService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }

  
    @PostMapping("") 
    public ResponseEntity<CreateNoteResponseDto> createNotes(@PathVariable("taskId") Integer taskId , @RequestBody CreateNoteDto body) {
        var note = noteService.addNoteForTask(taskId, body.title, body.body);
        return CreateNoteResponseDto.ok(new CreateNoteResponseDto(taskId, note));
    }

    // Error handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleErrors(Exception e) {

        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDto("Internal Server Error"));
    }

}
