package com.example.todolist.controller;

import com.example.todolist.dtos.TaskDTO;
import com.example.todolist.models.Task;
import com.example.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return this.taskService.list();
    }

    @GetMapping("{id}")
    public Task getOneTask(@PathVariable Long id) {
        return this.taskService.get(id);
    }

    @PostMapping
    public Task createTask(@RequestBody TaskDTO data) {
        return this.taskService.create(data);
    }

    @PutMapping("{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskDTO data) {
        return this.taskService.update(id, data);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        this.taskService.delete(id);
    }
}
