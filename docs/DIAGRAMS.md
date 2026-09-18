# Design Diagrams

## System Architecture
```text
+------------------+
|     CLI User     |
+--------+---------+
         |
         v
+------------------+
|    Main / UI     |
+---+----+----+----+
    |    |    |
    v    v    v
 TaskManager  ReportService  ReminderService
    |
    v
FileStorageService
    |
    v
 tasks.csv
```

## Workflow
```text
Start -> Load CSV -> Display Menu -> Choose Operation
  |-> Add -> Validate -> Store in ArrayList -> Menu
  |-> List/Search/Sort -> Read ArrayList -> Display -> Menu
  |-> Complete/Delete -> Validate ID -> Update ArrayList -> Menu
  |-> Analytics -> Generate report -> Menu
  |-> Reminder -> Start Thread -> Check deadlines -> Menu
  |-> Save & Exit -> Write CSV -> End
```

## Use Case Diagram (text form)
```text
Student --> (Add Task)
Student --> (View Tasks)
Student --> (Complete Task)
Student --> (Delete Task)
Student --> (Search Task)
Student --> (Sort by Due Date)
Student --> (View Analytics)
Student --> (Run Deadline Reminder)
Student --> (Save & Exit)
```

## Class Diagram (text form)
```text
Task
- id:int
- title:String
- category:String
- dueDate:LocalDate
- completed:boolean
+ markCompleted()
+ toCsv()
+ fromCsv()

TaskManager
- tasks:List<Task>
+ addTask()
+ find()
+ complete()
+ delete()
+ search()
+ sortedByDueDate()
+ completedCount()

FileStorageService --> TaskManager
ReportService --> TaskManager
ReminderService ..|> Runnable
Main --> TaskManager
Main --> FileStorageService
Main --> ReportService
Main --> ReminderService
```

## Sequence Diagram
```text
Student -> Main: Select Add Task
Main -> InputValidator: Validate fields
InputValidator --> Main: Valid
Main -> TaskManager: addTask()
TaskManager -> Task: create object
Task --> TaskManager: Task
TaskManager --> Main: Created task
Main --> Student: Display confirmation
```

## ER / Storage Design
Although the project is not a database project, its CSV storage can be represented as one entity:

`TASK(id PK, title, category, dueDate, completed)`
