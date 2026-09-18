package com.smartcampus.app;

import com.smartcampus.exception.InvalidTaskException;
import com.smartcampus.exception.TaskNotFoundException;
import com.smartcampus.model.Task;
import com.smartcampus.service.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "data/tasks.csv";
    private final TaskManager manager = new TaskManager();
    private final FileStorageService storage = new FileStorageService(DATA_FILE);
    private final ReportService report = new ReportService();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) { new Main().run(); }

    private void run() {
        try { storage.load(manager); } catch (Exception e) { System.out.println("Could not load saved data: " + e.getMessage()); }
        System.out.println("\n=== SMART CAMPUS TASK MANAGER ===");
        System.out.println("Java CLI project: Collections + File I/O + Exceptions + Multithreading");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> addTask();
                    case "2" -> list(manager.getAll());
                    case "3" -> completeTask();
                    case "4" -> deleteTask();
                    case "5" -> search();
                    case "6" -> list(manager.sortedByDueDate());
                    case "7" -> System.out.println(report.buildSummary(manager));
                    case "8" -> startReminderThread();
                    case "9" -> { storage.save(manager); System.out.println("Data saved. Goodbye!"); running = false; }
                    default -> System.out.println("Invalid option. Choose 1-9.");
                }
            } catch (TaskNotFoundException | InvalidTaskException | IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n1. Add Task\n2. List Tasks\n3. Complete Task\n4. Delete Task\n5. Search\n6. Sort by Due Date\n7. Analytics Report\n8. Run Deadline Reminder (Thread)\n9. Save & Exit");
        System.out.print("Choose an option: ");
    }

    private void addTask() throws InvalidTaskException {
        System.out.print("Title: "); String title = scanner.nextLine();
        System.out.print("Category: "); String category = scanner.nextLine();
        System.out.print("Due date (YYYY-MM-DD): "); String date = scanner.nextLine();
        System.out.println("Added: " + manager.addTask(title, category, date));
    }

    private void list(List<Task> tasks) {
        if (tasks.isEmpty()) { System.out.println("No tasks found."); return; }
        System.out.println("\n--- TASKS ---"); tasks.forEach(System.out::println);
    }

    private void completeTask() throws TaskNotFoundException {
        System.out.print("Task ID: "); int id = Integer.parseInt(scanner.nextLine());
        manager.complete(id); System.out.println("Task marked completed.");
    }

    private void deleteTask() throws TaskNotFoundException {
        System.out.print("Task ID: "); int id = Integer.parseInt(scanner.nextLine());
        manager.delete(id); System.out.println("Task deleted.");
    }

    private void search() {
        System.out.print("Keyword: "); list(manager.search(scanner.nextLine()));
    }

    private void startReminderThread() {
        Thread reminder = new Thread(new ReminderService(manager), "DeadlineReminder");
        reminder.start();
        try { reminder.join(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); System.out.println("Reminder interrupted."); }
    }
}
