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

class MarkCommandTest {
    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        storage = new Storage("test.txt"); // Stubbed, should not actually read/write
    }

    @Test
    void executeAndReturn_validIndex_marksTask() throws LunaException {
        Task task = new Todo("Finish assignment");
        taskList.addTask(task);

        MarkCommand markCommand = new MarkCommand("mark 1");
        String result = markCommand.executeAndReturn(taskList, storage);

        assertEquals("Yay!~ I've marked this task as done:\n[T][X] Finish assignment", result);
    }

    @Test
    void executeAndReturn_invalidIndex_throwsException() throws LunaException {
        MarkCommand markCommand = new MarkCommand("mark 2");

        LunaException exception = assertThrows(LunaException.class, (
                ) -> markCommand.executeAndReturn(taskList, storage)
        );

        assertEquals("Check the task number again to make sure it's correct~", exception.getMessage());
    }
}
