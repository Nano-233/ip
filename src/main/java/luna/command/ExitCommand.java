package luna.command;

import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Handles exiting the application.
 */
public class ExitCommand extends Command {

    @Override
    public String executeAndReturn(TaskList tasks, Storage storage) {
        System.exit(0);
        return "Bye~";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
