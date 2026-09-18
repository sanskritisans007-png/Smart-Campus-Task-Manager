package com.smartcampus.service;

import com.smartcampus.exception.InvalidTaskException;
import com.smartcampus.exception.TaskNotFoundException;
import com.smartcampus.model.Task;
import com.smartcampus.util.InputValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task addTask(String title, String category, String date) throws InvalidTaskException {
        title = InputValidator.text(title, "Title");
        category = InputValidator.text(category, "Category");
        LocalDate due = InputValidator.date(date);
        Task t = new Task(nextId++, title, category, due);
        tasks.add(t);
        return t;
    }

    public void addLoadedTask(Task task) {
        tasks.add(task);
        nextId = Math.max(nextId, task.getId() + 1);
    }

    public List<Task> getAll() { return new ArrayList<>(tasks); }

    public Task find(int id) throws TaskNotFoundException {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void complete(int id) throws TaskNotFoundException { find(id).markCompleted(); }

    public void delete(int id) throws TaskNotFoundException { tasks.remove(find(id)); }

    public List<Task> search(String keyword) {
        String k = keyword.toLowerCase();
        return tasks.stream().filter(t -> t.getTitle().toLowerCase().contains(k) || t.getCategory().toLowerCase().contains(k)).collect(Collectors.toList());
    }

    public List<Task> sortedByDueDate() {
        return tasks.stream().sorted(Comparator.comparing(Task::getDueDate)).collect(Collectors.toList());
    }

    public long completedCount() { return tasks.stream().filter(Task::isCompleted).count(); }
    public long pendingCount() { return tasks.size() - completedCount(); }
    public int size() { return tasks.size(); }
}
