package luna.command;

import luna.task.TaskList;
import luna.ui.Ui;
import luna.storage.Storage;

public class ListCommand extends Command {
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    tasks.printList();
  }
}
