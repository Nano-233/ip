package luna;

import luna.command.Command;
import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Luna: A task manager application with CLI and GUI support.
 */
public class Luna {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initializes Luna with a storage file.
     *
     * @param filePath Path to the storage file.
     */
    public Luna(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (LunaException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns Luna's response to user input.
     *
     * @param input The user's command.
     * @return The response message.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.executeAndReturn(tasks, storage);
        } catch (LunaException e) {
            return e.getMessage();
        }
    }
}
