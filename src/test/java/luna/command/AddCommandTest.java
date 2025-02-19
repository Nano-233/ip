package luna.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;

class AddCommandTest {

    @Test
    void executeAndReturn_validTodoCommand_success() throws LunaException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("test.txt");
        AddCommand command = new AddCommand("todo Read book");

        String result = command.executeAndReturn(taskList, storage);
        assertTrue(result.contains("I've added this task"));
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    void executeAndReturn_invalidCommand_throwsException() {
        try {
            new AddCommand("todo");
            fail();
        } catch (LunaException e) {
            assertEquals("Incorrect format :<\n"
                                 + "Task description cannot be empty.\n"
                                 + "Correct format: `todo <description>`", e.getMessage());
        }
    }
}
