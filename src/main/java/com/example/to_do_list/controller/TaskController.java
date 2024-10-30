package com.example.to_do_list.controller;

import com.example.to_do_list.dtos.TaskDTO;
import com.example.to_do_list.model.Task;
import com.example.to_do_list.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> listAll() {
        List<Task> allTasks = taskService.getAllTask();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task = taskService.getOneTask(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task data) {
        Task task = taskService.createTask(data);
        return ResponseEntity.ok(task);
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(TaskDTO data, Long id) {
        Task updatedTask = taskService.updateTask(data, id);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping
    public ResponseEntity deleteTask(Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
