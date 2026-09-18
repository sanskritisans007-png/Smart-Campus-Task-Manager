package com.smartcampus.model;

import java.time.LocalDate;

public class Task {
    private final int id;
    private String title;
    private String category;
    private LocalDate dueDate;
    private boolean completed;

    public Task(int id, String title, String category, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isCompleted() { return completed; }
    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void markCompleted() { this.completed = true; }

    public String toCsv() {
        return id + "," + title.replace(",", " ") + "," + category.replace(",", " ") + "," + dueDate + "," + completed;
    }

    public static Task fromCsv(String line) {
        String[] p = line.split(",", -1);
        if (p.length != 5) throw new IllegalArgumentException("Invalid task record");
        Task t = new Task(Integer.parseInt(p[0]), p[1], p[2], LocalDate.parse(p[3]));
        if (Boolean.parseBoolean(p[4])) t.markCompleted();
        return t;
    }

    @Override public String toString() {
        return String.format("[%d] %-28s | %-12s | Due: %s | %s", id, title, category, dueDate,
                completed ? "COMPLETED" : "PENDING");
    }
}
