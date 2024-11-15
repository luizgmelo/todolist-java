package com.example.todolist.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "tb_task")
@NoArgsConstructor
@Getter
@Setter
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean done;

    @Column(nullable = false)
    private Integer priority;

    public Task(String title, String description, Boolean done, Integer priority) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.priority = priority;
    }
}
