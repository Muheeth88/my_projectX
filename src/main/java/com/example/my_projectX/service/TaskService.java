package com.example.my_projectX.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.my_projectX.entities.TaskEntity;

@Service
public class TaskService {

    public ArrayList<TaskEntity> tasks = new ArrayList<>();

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }

    public int taskId = 1;
    
    public TaskEntity addTask( String title, String description, String deadline) {
    	TaskEntity task = new TaskEntity();
    	task.id = taskId;
        task.title = title;
        task.description = description;
        task.deadline = deadline;
        task.compleated = false;
        tasks.add(task);
    	taskId++;
        return task;
    }

    public TaskEntity getTaskById(int id) {
        for (TaskEntity task : tasks) {
            if (task.id == id) {
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(int id, String description, String deadline, Boolean compleated) {

        TaskEntity task = getTaskById(id);
        
        if(task == null) {
            return null;
        }

        task.description = description;
        task.deadline = deadline;
        task.compleated = compleated;

        return task;

    }

 
}
