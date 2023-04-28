package com.example.my_projectX.dto;

import org.springframework.http.ResponseEntity;

import com.example.my_projectX.entities.NoteEntity;

public class CreateNoteResponseDto {
    public NoteEntity note ;
    public Integer taskId;
    public CreateNoteResponseDto(Integer taskId, NoteEntity note) {
        this.taskId = taskId;
        this.note = note;
    }
    public static ResponseEntity<CreateNoteResponseDto> ok(CreateNoteResponseDto createNoteResponseDto) {
        return null;
    }
}
