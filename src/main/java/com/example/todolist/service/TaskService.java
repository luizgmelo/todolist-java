package com.example.todolist.service;

import com.example.todolist.dtos.TaskDTO;
import com.example.todolist.exception.ForbiddenOperationException;
import com.example.todolist.models.Task;
import com.example.todolist.models.User;
import com.example.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> list() {
        return this.taskRepository.findAll();
    }

    public Task get(Long id) {
        Optional<Task> opTask = this.taskRepository.findById(id);
        return opTask.orElse(null);
    }

    public Task create(TaskDTO data, User creator) {
        Task newTask = new Task(data.title(), data.description(), data.done(), data.priority(), creator);
        return this.taskRepository.save(newTask);
    }

    public Task update(Long id, TaskDTO data, User creator) {
        Optional<Task> opTask = this.taskRepository.findById(id);
        if (opTask.isEmpty()) {
            return null;
        }
        Task oldTask = opTask.get();
        if (oldTask.getCreator().getUsername().equals(creator.getUsername())) {
            throw new ForbiddenOperationException();
        }
        oldTask.setTitle(data.title());
        oldTask.setDescription(data.description());
        this.taskRepository.save(oldTask);
        return oldTask;
    }

    public void delete(Long id) {
        this.taskRepository.deleteById(id);
    }
}
