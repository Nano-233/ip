package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;

/**
 * Represents an abstract command that can be executed on the task list.
 * All commands should extend this class and implement the execute method.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The task list to modify.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler to save changes.
     * @throws LunaException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LunaException;

    /**
     * Determines whether this command should terminate the application.
     *
     * @return {@code true} if the command should exit the application, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
