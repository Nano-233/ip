package luna.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Unit tests for the {@link AddCommand} class.
 * Ensures that tasks are correctly added to the task list
 * and that invalid commands throw appropriate exceptions.
 */
class AddCommandTest {

    /**
     * Tests that a valid "todo" command adds a task successfully.
     *
     * @throws LunaException If the command execution encounters an issue.
     */
    @Test
    void executeAndReturn_validTodoCommand_success() throws LunaException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("test.txt"); // Stubbed storage, should not actually read/write
        AddCommand command = new AddCommand("todo Read book");

        String result = command.executeAndReturn(taskList, storage);

        assertTrue(result.contains("I've added this task"),
                "Expected confirmation message for added task.");
        assertEquals(1, taskList.getTasks().size(),
                "Task list should contain exactly one task after adding.");
    }

    /**
     * Tests that an invalid "todo" command (missing description) throws an exception.
     */
    @Test
    void executeAndReturn_invalidCommand_throwsException() {
        try {
            new AddCommand("todo");
            fail("Expected LunaException due to missing task description.");
        } catch (LunaException e) {
            assertEquals("Incorrect format :<\n"
                                 + "Task description cannot be empty.\n"
                                 + "Correct format: `todo <description>`",
                    e.getMessage(), "Exception message should match expected format guidance.");
        }
    }
}
