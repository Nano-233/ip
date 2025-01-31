package luna.command;

import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
