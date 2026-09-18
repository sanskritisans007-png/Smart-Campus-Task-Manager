package com.smartcampus.service;

import com.smartcampus.model.Task;
import java.time.LocalDate;

public class ReminderService implements Runnable {
    private final TaskManager manager;
    public ReminderService(TaskManager manager) { this.manager = manager; }

    @Override public void run() {
        LocalDate today = LocalDate.now();
        System.out.println("\n[Reminder Thread] Checking upcoming deadlines...");
        manager.sortedByDueDate().stream()
                .filter(t -> !t.isCompleted() && !t.getDueDate().isAfter(today.plusDays(2)))
                .forEach(t -> System.out.println("  ! Reminder: " + t.getTitle() + " is due on " + t.getDueDate()));
        System.out.println("[Reminder Thread] Check complete.");
    }
}
