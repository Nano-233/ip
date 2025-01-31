package luna.command;

import luna.task.TaskList;
import luna.ui.Ui;
import luna.storage.Storage;

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
