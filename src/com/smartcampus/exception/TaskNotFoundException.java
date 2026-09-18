package com.smartcampus.exception;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(int id) {
        super("No task exists with ID " + id + ".");
    }
}
