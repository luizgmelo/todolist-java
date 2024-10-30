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

    @Column(nullable = false, length = 20)
    private String title;
    @Column(nullable = false)
    private String description;
    private boolean done;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Task() {}

    public Task(String title, String description, LocalDate createdAt, LocalDate updatedAt) {
        this.title = title;
        this.description = description;
        this.done = false;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
