package com.tiego.makaleng.model;

public class TaskEntry {

    private int id;
    private String description;
    private int priority;

    public TaskEntry(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    public TaskEntry(int id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
