package com.example.my_projectX.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.my_projectX.dto.ErrorResponseDto;
import com.example.my_projectX.dto.createTaskDto;
import com.example.my_projectX.dto.updateTaskDto;
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
    public ResponseEntity<TaskEntity> addTask(@RequestBody createTaskDto body) {
        var task = taskService.addTask(body.title, body.description, body.deadline);
        return ResponseEntity.ok(task);
    }

    // Edit Task
    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id ,@RequestBody updateTaskDto body) {
        var task = taskService.updateTask(id, body.description, body.deadline, body.compleated);

        return ResponseEntity.ok(task);
    }

    // Error handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleErrors(Exception e) {

        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDto("Internal Server Error"));
    }
}
