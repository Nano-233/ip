package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.Task;
import luna.task.TaskList;
import luna.ui.Ui;

/**
 * Represents a command to delete a specific task or all tasks from the task list.
 */
public class DeleteCommand extends Command {
    private final Integer index; // Can be null if "all" is used
    private final boolean deleteAll;

    /**
     * Constructs a DeleteCommand instance.
     *
     * @param input The user input specifying which task to delete or if all tasks should be deleted.
     * @throws LunaException If the format is invalid or the task number is not a valid integer.
     */
    public DeleteCommand(String input) throws LunaException {
        String[] parts = input.split(" ", 2);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Correct format for delete is `delete <task number>` or `delete all`");
        }

        if (parts[1].equalsIgnoreCase("all")) {
            this.index = null;
            this.deleteAll = true;
        } else {
            try {
                this.index = Integer.parseInt(parts[1]) - 1;
                this.deleteAll = false;
            } catch (NumberFormatException e) {
                throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                        "Correct format for delete is `delete <task number>` or `delete all`");
            }
        }
    }

    /**
     * Executes the delete command, removing either a specific task or all tasks from the list.
     *
     * @param tasks   The task list to modify.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler to save changes.
     * @throws LunaException If there are no tasks to delete or if the task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LunaException {
        if (deleteAll) {
            if (tasks.getTasks().isEmpty()) {
                throw new LunaException(LunaException.ErrorType.INVALID_FORMAT, "There are no tasks to delete!");
            }

            tasks.getTasks().clear();
            storage.saveTasks(tasks.getTasks());

            System.out.println("Oki~ I've removed all tasks!");
            System.out.println("Now you have 0 tasks left in the list~");
        } else {
            if (index < 0 || index >= tasks.getTasks().size()) {
                throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
            }

            Task removedTask = tasks.getTasks().remove((int) index);
            storage.saveTasks(tasks.getTasks());

            System.out.println("Oki~ I've removed this task!:");
            System.out.println(removedTask);
            System.out.println("Now you have " + tasks.getTasks().size() + " tasks left in the list~");
        }
    }
}
