package luna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import luna.LunaException;

/**
 * Tests the {@link TaskList} class to ensure correct functionality.
 */
class TaskListTest {

    /**
     * Ensures that a task is successfully added to the {@link TaskList}.
     * Verifies that the task count increases and the correct task is stored.
     */
    @Test
    void addTask_taskAdded_success() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Write report");
        taskList.addTask(task);

        assertEquals(1, taskList.getTasks().size(), "Task count should be 1 after adding a task.");
        assertEquals("[T][ ] Write report", taskList.getTasks().get(0).toString(),
                "Task should match the expected format.");
    }

    /**
     * Ensures that a task is successfully deleted from the {@link TaskList}.
     * Verifies that the deleted task is returned and the task count decreases.
     *
     * @throws LunaException if an invalid task index is accessed (not expected in this test).
     */
    @Test
    void deleteTask_taskDeleted_success() throws LunaException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Write report");
        taskList.addTask(task);

        Task deletedTask = taskList.deleteTask(0);
        assertEquals("Write report", deletedTask.getDescription(),
                "Deleted task description should match the original.");
        assertEquals(0, taskList.getTasks().size(), "Task count should be 0 after deletion.");
    }
}
