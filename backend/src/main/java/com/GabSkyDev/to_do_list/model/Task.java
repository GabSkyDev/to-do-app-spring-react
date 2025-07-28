package com.GabSkyDev.to_do_list.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_tb")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean state;
    @Enumerated(EnumType.STRING)
    private Priority priority;

    public Task(){}

    public Task(Long id, String name, String description, Boolean state, Priority priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
