package luna.command;

import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param tasks   Task list containing tasks to be listed.
     * @param ui      User interface for displaying messages.
     * @param storage Storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
