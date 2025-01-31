package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LunaException;

    public boolean isExit() {
        return false;
    }
}
