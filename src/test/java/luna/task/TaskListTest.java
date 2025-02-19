package luna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import luna.LunaException;

class TaskListTest {

    @Test
    void addTask_taskAdded_success() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Write report");
        taskList.addTask(task);

        assertEquals(1, taskList.getTasks().size());
        assertEquals("[T][ ] Write report", taskList.getTasks().get(0).toString());
    }

    @Test
    void deleteTask_taskDeleted_success() throws LunaException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Write report");
        taskList.addTask(task);

        Task deletedTask = taskList.deleteTask(0);
        assertEquals("Write report", deletedTask.getDescription());
        assertEquals(0, taskList.getTasks().size());
    }
}
