package com.smartcampus.util;

import com.smartcampus.exception.InvalidTaskException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class InputValidator {
    private InputValidator() {}

    public static String text(String value, String field) throws InvalidTaskException {
        if (value == null || value.trim().isEmpty()) throw new InvalidTaskException(field + " cannot be empty.");
        return value.trim();
    }

    public static LocalDate date(String value) throws InvalidTaskException {
        try { return LocalDate.parse(value.trim()); }
        catch (DateTimeParseException e) { throw new InvalidTaskException("Date must use YYYY-MM-DD format."); }
    }
}
