package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Represents an abstract command that can be executed on the task list.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The task list to modify.
     * @param storage The storage handler to save changes.
     * @return The response message.
     * @throws LunaException If an error occurs during execution.
     */
    public abstract String executeAndReturn(TaskList tasks, Storage storage) throws LunaException;

    /**
     * Determines whether this command should terminate the application.
     *
     * @return {@code true} if the command should exit the application, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
