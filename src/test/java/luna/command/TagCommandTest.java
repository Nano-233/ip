package luna.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;
import luna.task.Todo;

class TagCommandTest {
    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        storage = new Storage("test.txt"); // Stubbed, should not actually read/write
        taskList.addTask(new Todo("Finish report"));
    }

    @Test
    void executeAndReturn_validIndex_tagsTask() throws LunaException {
        TagCommand tagCommand = new TagCommand("tag 1 important");
        String result = tagCommand.executeAndReturn(taskList, storage);

        assertEquals("Oki~ I've added the tag `important` to this task:\n"
                             + "[T][ ] Finish report #important", result);
    }

    @Test
    void executeAndReturn_invalidIndex_throwsException() throws LunaException {
        TagCommand tagCommand = new TagCommand("tag 2 urgent");

        LunaException exception = assertThrows(LunaException.class, (
                ) -> tagCommand.executeAndReturn(taskList, storage)
        );

        assertEquals("Check the task number again to "
                             + "make sure it's correct~Invalid task number.", exception.getMessage());
    }
}
