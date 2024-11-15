package com.example.todolist.dtos;

public record TaskDTO(String title, String description, Boolean done, Integer priority) {
}
