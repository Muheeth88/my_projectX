package com.example.my_projectX.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.my_projectX.dto.taskDto;
import com.example.my_projectX.entities.TaskEntity;
import com.example.my_projectX.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    // get all tasks
    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    // get task by Id
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id) {
        var task = taskService.getTaskById(id);
        if( task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    //  Create New Task
    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody taskDto body) {
        var task = taskService.addTask(body.title, body.description, body.deadline);
        return ResponseEntity.ok(task);
    }
}
