package com.example.todolist.controller;

import com.example.todolist.dtos.TaskDTO;
import com.example.todolist.models.Task;
import com.example.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(this.taskService.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getOneTask(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.taskService.get(id));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskService.create(data));
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(this.taskService.update(id, data));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        this.taskService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
