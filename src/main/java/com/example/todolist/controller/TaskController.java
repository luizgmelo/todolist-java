package com.example.todolist.controller;

import com.example.todolist.dtos.TaskDTO;
import com.example.todolist.models.Task;
import com.example.todolist.service.TaskService;
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
        Task task = this.taskService.get(id);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskService.create(data));
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO data) {
        Task updatedTask = this.taskService.update(id, data);

        if (updatedTask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        this.taskService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
