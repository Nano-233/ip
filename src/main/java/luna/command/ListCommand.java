package luna.command;

import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Handles listing all tasks in the task list.
 */
public class ListCommand extends Command {

    @Override
    public String executeAndReturn(TaskList tasks, Storage storage) {
        return tasks.printList();
    }
}
