package luna.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import luna.LunaException;
import luna.task.TaskList;
import luna.task.Todo;

/**
 * Unit tests for the {@link FindCommand} class.
 * Ensures that searching for tasks using keywords returns the expected results.
 */
class FindCommandTest {
    private TaskList taskList;

    /**
     * Sets up a task list with predefined tasks before each test.
     */
    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Todo("Buy groceries"));
        taskList.addTask(new Todo("Read book"));
        taskList.addTask(new Todo("Complete homework"));
    }

    /**
     * Tests that searching with a valid keyword returns matching tasks.
     *
     * @throws LunaException If an unexpected error occurs during execution.
     */
    @Test
    void executeAndReturn_validKeyword_findsMatchingTasks() throws LunaException {
        FindCommand findCommand = new FindCommand("find book");
        String result = findCommand.executeAndReturn(taskList, null);

        assertEquals("Here are the matching tasks in your list:\n1. [T][ ] Read book", result);
    }

    /**
     * Tests that searching with a keyword that has no matches returns a "no matches" message.
     *
     * @throws LunaException If an unexpected error occurs during execution.
     */
    @Test
    void executeAndReturn_noMatch_returnsNoMatchesMessage() throws LunaException {
        FindCommand findCommand = new FindCommand("find swimming");
        String result = findCommand.executeAndReturn(taskList, null);

        assertEquals("No matching tasks found!", result);
    }
}
