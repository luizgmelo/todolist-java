package com.example.todolist.service;


import com.example.todolist.models.Task;
import com.example.todolist.repositories.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.todolist.constants.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;


    @Test
    @DisplayName("Should return a list with tasks")
    void listTasks_WhenExists_ReturnListOfTask() {
        when(taskRepository.findAll()).thenReturn(List.of(TASK));

        List<Task> sut = taskService.list();

        assertThat(sut.get(0)).isEqualTo(TASK);
        assertThat(sut).isNotEmpty();
    }

    @Test
    @DisplayName("Should return a empty list")
    void listTasks_WhenDontExists_ReturnEmptyList() {
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());

        List<Task> sut = taskService.list();

        assertThat(sut).isEmpty();
    }


    @Test
    @DisplayName("Should return one task if exists")
    void geTask_ByExistingId_ReturnTask() {
        when(taskRepository.findById(any())).thenReturn(Optional.of(TASK));

        Task sut = taskService.get(any());

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(TASK);
    }

    @Test
    @DisplayName("Should return null if task not exists")
    void getTask_ByUnexistingId_ReturnNull() {
        when(taskRepository.findById(any())).thenReturn(Optional.empty());

        Task sut = taskService.get(any());

        assertThat(sut).isNull();
    }

    @Test
    @DisplayName("Should create a new task with valid data")
    void createTask_WithValidData_ReturnsTask() {
        when(taskRepository.save(TASK)).thenReturn(TASK);

        Task sut = taskService.create(TASK_DTO, TASK.getCreator());

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(TASK);
    }


    @Test
    @DisplayName("Should throw exception when create a new task with invalid data")
    void createTask_WithInvalidData_ReturnsTask() {
    }

    @Test
    @DisplayName("Should update a task")
    void update() {
        when(taskRepository.findById(any())).thenReturn(Optional.of(TASK));
        when(taskRepository.save(any())).thenReturn(TASK);

        Task sut = taskService.update(TASK.getId(), TASK_DTO, TASK.getCreator());

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(TASK);
    }
}