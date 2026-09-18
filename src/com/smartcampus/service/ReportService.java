package com.smartcampus.service;

import com.smartcampus.model.Task;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {
    public String buildSummary(TaskManager manager) {
        StringBuilder s = new StringBuilder();
        s.append("\n===== TASK ANALYTICS =====\n");
        s.append("Total tasks     : ").append(manager.size()).append('\n');
        s.append("Completed tasks : ").append(manager.completedCount()).append('\n');
        s.append("Pending tasks   : ").append(manager.pendingCount()).append('\n');
        List<Task> overdue = manager.getAll().stream().filter(t -> !t.isCompleted() && t.getDueDate().isBefore(LocalDate.now())).toList();
        s.append("Overdue tasks   : ").append(overdue.size()).append('\n');
        Map<String, Long> categories = manager.getAll().stream().collect(Collectors.groupingBy(Task::getCategory, Collectors.counting()));
        s.append("By category     : ").append(categories).append('\n');
        return s.toString();
    }
}
