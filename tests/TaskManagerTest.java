package tests;

import com.smartcampus.exception.InvalidTaskException;
import com.smartcampus.exception.TaskNotFoundException;
import com.smartcampus.service.TaskManager;

public class TaskManagerTest {
    public static void main(String[] args) throws Exception {
        TaskManager m = new TaskManager();
        m.addTask("DBMS Assignment", "Academic", "2030-09-20");
        m.addTask("Java Revision", "Academic", "2030-09-21");
        assert m.size() == 2 : "Add task failed";
        m.complete(1);
        assert m.completedCount() == 1 : "Complete failed";
        assert m.search("java").size() == 1 : "Search failed";
        boolean missing = false;
        try { m.delete(999); } catch (TaskNotFoundException e) { missing = true; }
        assert missing : "Exception handling failed";
        boolean invalid = false;
        try { m.addTask("", "Academic", "2030-09-21"); } catch (InvalidTaskException e) { invalid = true; }
        assert invalid : "Validation failed";
        System.out.println("ALL TESTS PASSED");
    }
}
