package luna.command;

import luna.LunaException;
import luna.task.TaskList;
import luna.ui.Ui;
import luna.storage.Storage;

public abstract class Command {
  public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LunaException;

  public boolean isExit() {
    return false;
  }
}
