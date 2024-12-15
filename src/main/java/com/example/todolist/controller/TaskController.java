package com.example.todolist.controller;

import com.example.todolist.dtos.TaskDTO;
import com.example.todolist.exception.ForbiddenOperationException;
import com.example.todolist.models.Task;
import com.example.todolist.models.User;
import com.example.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//<TODO> Responses should be DTOS
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
        User creator = getAuthenticatedUser();

        if (creator == null) {
            throw new ForbiddenOperationException();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskService.create(data, creator));
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO data) {
        User creator = getAuthenticatedUser();

        if (creator == null) {
            throw new ForbiddenOperationException();
        }

        Task updatedTask = this.taskService.update(id, data, creator);

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

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        } else {
            return null;
        }
    }
}
