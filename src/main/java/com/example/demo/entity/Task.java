package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    private String title;
    
    @Size(max = 500, message = "Description can't be longer than 500 characters")
    private String description; 

    @Enumerated(EnumType.STRING) 
    @NotNull(message = "Status cannot be null")
    private Status status; 

    public Task() {}

    public Task(String title, String description, Status status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
}
