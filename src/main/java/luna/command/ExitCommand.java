package luna.command;

import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;

/**
 * Represents a command that exits the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, displaying a farewell message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye~");
    }

    /**
     * Indicates that this command will terminate the program.
     *
     * @return {@code true}, as this command signals program termination.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
