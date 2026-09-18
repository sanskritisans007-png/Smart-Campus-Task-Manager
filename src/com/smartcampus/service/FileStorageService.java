package com.smartcampus.service;

import com.smartcampus.model.Task;
import java.io.*;
import java.nio.file.*;

public class FileStorageService {
    private final Path file;
    public FileStorageService(String filename) { this.file = Paths.get(filename); }

    public void save(TaskManager manager) throws IOException {
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) Files.createDirectories(parent);
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write("id,title,category,dueDate,completed");
            writer.newLine();
            for (Task t : manager.getAll()) { writer.write(t.toCsv()); writer.newLine(); }
        }
    }

    public void load(TaskManager manager) throws IOException {
        if (!Files.exists(file)) return;
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line; boolean first = true;
            while ((line = reader.readLine()) != null) {
                if (first) { first = false; continue; }
                if (!line.isBlank()) manager.addLoadedTask(Task.fromCsv(line));
            }
        }
    }
}
