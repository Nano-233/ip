package luna.command;

import luna.LunaException;
import luna.task.*;
import luna.ui.Ui;
import luna.storage.Storage;

public class AddCommand extends Command {
  private final Task task;

  public AddCommand(String input) throws LunaException {
    String[] parts = input.split(" ", 2);
    String[] details = parts.length > 1 ? parts[1].split(" /by | /from | /to ", 3) : new String[0];

    switch (parts[0]) {
      case "todo":
        if (details.length < 1 || details[0].trim().isEmpty()) {
          throw new LunaException(LunaException.ErrorType.INVALID_FORMAT, "Correct format: `todo <description>`");
        }
        task = new Todo(details[0]);
        break;
      case "deadline":
        if (details.length < 2 || details[0].trim().isEmpty() || details[1].trim().isEmpty()) {
          throw new LunaException(LunaException.ErrorType.INVALID_FORMAT, "Correct format: `deadline <description> /by <dd/mm/yyyy>`");
        }
        task = new Deadline(details[0], details[1]);
        break;
      case "event":
        if (details.length < 3 || details[0].trim().isEmpty() || details[1].trim().isEmpty() || details[2].trim().isEmpty()) {
          throw new LunaException(LunaException.ErrorType.INVALID_FORMAT, "Correct format: `event <description> /from <dd/mm/yyyy> /to <dd/mm/yyyy>`");
        }
        task = new Event(details[0], details[1], details[2]);
        break;
      default:
        throw new LunaException(LunaException.ErrorType.UNKNOWN_COMMAND, "");
    }
  }


  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    tasks.addTask(task);
    storage.saveTasks(tasks.getTasks());
    System.out.println("Got it~ I've added this task:");
    System.out.println(task);
    System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list!!");
  }
}
