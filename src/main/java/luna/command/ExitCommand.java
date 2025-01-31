package luna.command;

import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye~");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
