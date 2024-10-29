package com.example.to_do_list.service;

import com.example.to_do_list.dtos.TaskDTO;
import com.example.to_do_list.model.Task;
import com.example.to_do_list.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(TaskDTO data) {
        LocalDate currentDate = getCurrentDate();
        Task newTask = new Task(data.description(), data.done(), currentDate, currentDate);
        taskRepository.save(newTask);
        return newTask;
    }

    public Task getOneTask(Long id) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            return taskOpt.get();
        }
        return null;
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Task updateTask(TaskDTO data, Long id) {
        Optional<Task> taskOldOpt = taskRepository.findById(id);
        if (taskOldOpt.isPresent()) {
            Task taskOld = taskOldOpt.get();
            taskOld.setTitle(data.title());
            taskOld.setDescription(data.description());
            taskOld.setDone(data.done());
            taskOld.setUpdatedAt(getCurrentDate());
            taskRepository.save(taskOld);
            return taskOld;
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now(ZoneId.of("America/Sao_Paulo"));
    }

}