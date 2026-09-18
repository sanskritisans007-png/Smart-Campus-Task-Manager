# Smart Campus Task Manager

A command-line Java application for managing academic and personal campus tasks. It demonstrates Java Collections, OOP, exception handling, file I/O, String processing, streams, user-defined packages, and multithreading.

## Features
- Add, list, complete and delete tasks
- Search tasks by title/category
- Sort tasks by due date
- Generate analytics: total, completed, pending, overdue and category counts
- Run a deadline reminder in a separate Java thread
- Persist tasks to `data/tasks.csv`
- Input validation and custom exceptions

## Technologies
- Java 17+ (works with modern JDKs)
- Java Collections Framework (`ArrayList`)
- Streams and lambdas
- `java.io` / `java.nio.file` for persistence
- `java.time` for dates
- Threads and `Runnable`
- Git/GitHub

## Project Structure
```text
src/com/smartcampus/
├── app/Main.java
├── model/Task.java
├── service/TaskManager.java
├── service/FileStorageService.java
├── service/ReportService.java
├── service/ReminderService.java
├── util/InputValidator.java
└── exception/
    ├── InvalidTaskException.java
    └── TaskNotFoundException.java
tests/TaskManagerTest.java
data/tasks.csv
```

## Requirements
Install JDK 17 or later and verify:
```bash
java -version
javac -version
```

## Run
From the project root:
```bash
./run.sh
```
On Windows PowerShell, compile manually:
```powershell
Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue
New-Item -ItemType Directory out | Out-Null
javac -d out (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object {$_.FullName})
java -cp out com.smartcampus.app.Main
```

## Test
Linux/macOS:
```bash
./test.sh
```
Windows PowerShell:
```powershell
Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue
New-Item -ItemType Directory out | Out-Null
javac -d out (Get-ChildItem -Recurse src,tests -Filter *.java | ForEach-Object {$_.FullName})
java -ea -cp out tests.TaskManagerTest
```
Expected result: `ALL TESTS PASSED`.

## Input Format
Dates must be entered as `YYYY-MM-DD`, for example `2026-09-25`.

## GitHub
Make the repository **Public** before submission. Submit only the repository root URL in the format:
`https://github.com/<github-username>/<repo-name>`

## Academic Relevance
The project applies Java syllabus concepts: exception handling, try/catch, user-defined packages, Strings, 1-D collections, I/O streams/file handling, multithreading, synchronization-ready service separation, and modular Java programming.
