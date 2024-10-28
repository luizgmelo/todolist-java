package com.example.to_do_list.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Entity
@Table(name = "TB_TASK")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;
    private boolean done;
    private LocalDate createdAt;

    private Task() {}

    public Task(String description, boolean done, LocalDate createdAt) {
        this.description = description;
        this.done = done;
        this.createdAt = createdAt;
    }
}
