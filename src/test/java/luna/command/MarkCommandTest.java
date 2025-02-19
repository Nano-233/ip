package luna.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.Task;
import luna.task.TaskList;
import luna.task.Todo;

/**
 * Unit tests for the {@link MarkCommand} class.
 * Ensures that marking tasks as done behaves as expected.
 */
class MarkCommandTest {
    private TaskList taskList;
    private Storage storage;

    /**
     * Sets up a new task list and a stub storage before each test.
     * The storage file is stubbed to prevent actual file operations.
     */
    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        storage = new Storage("test.txt");
    }

    /**
     * Tests that a valid task index correctly marks the task as done.
     *
     * @throws LunaException If an unexpected error occurs during marking.
     */
    @Test
    void executeAndReturn_validIndex_marksTask() throws LunaException {
        Task task = new Todo("Finish assignment");
        taskList.addTask(task);

        MarkCommand markCommand = new MarkCommand("mark 1");
        String result = markCommand.executeAndReturn(taskList, storage);

        assertEquals("Yay!~ I've marked this task as done:\n[T][X] Finish assignment", result);
    }

    /**
     * Tests that an invalid task index results in an exception.
     *
     * @throws LunaException If an error occurs while marking.
     */
    @Test
    void executeAndReturn_invalidIndex_throwsException() throws LunaException {
        MarkCommand markCommand = new MarkCommand("mark 2");

        LunaException exception = assertThrows(LunaException.class, (
                ) -> markCommand.executeAndReturn(taskList, storage)
        );

        assertEquals("Check the task number again to make sure it's correct~", exception.getMessage());
    }
}
