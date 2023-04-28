package com.example.my_projectX.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.example.my_projectX.entities.NoteEntity;
import com.example.my_projectX.entities.TaskEntity;

@Service
public class NoteService {

    private TaskService taskService;
    private HashMap<Integer, TaskNotesHolder> taskNoteHolders = new HashMap<>();

    public NoteService(TaskService taskService) {
        this.taskService = taskService;
    }

    class TaskNotesHolder {
        protected int noteId = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();
    }

    // getNotes
    public ArrayList<NoteEntity> getNotesForTask(int taskId) {
        TaskEntity task = taskService.getTaskById(taskId);
        if (task == null) {
            System.out.println("it stopped here");
            return null;
        }

        if(taskNoteHolders.get(taskId) == null) {
            taskNoteHolders.put(taskId, new TaskNotesHolder());
        }

        return taskNoteHolders.get(taskId).notes;
    }

    // addNotes
    public NoteEntity addNoteForTask(int taskId, String title, String body) {
        TaskEntity task = taskService.getTaskById(taskId);
        if (task == null) {
            return null;
        }
        if(taskNoteHolders.get(taskId) == null) {
            taskNoteHolders.put(taskId, new TaskNotesHolder());
        }

        TaskNotesHolder taskNotesHolder = taskNoteHolders.get(taskId);
        NoteEntity note = new NoteEntity();
        note.id = taskId;
        note.title = title;
        note.body = body;
        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;
        return note;
    }
}
