package com.example.todolist.dtos;

import com.example.todolist.models.UserRole;

public record RegisterRequestDTO(String name, String email, String password, UserRole role) {
}
