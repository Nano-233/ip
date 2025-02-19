package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.Task;
import luna.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final Integer index;
    private final boolean isDeleteAll;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param input The input command string.
     * @throws LunaException If the input format is invalid.
     */
    public DeleteCommand(String input) throws LunaException {
        String[] parts = input.split(" ", 2);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Correct format for delete is `delete <task number>` or `delete all`");
        }

        if (parts[1].equalsIgnoreCase("all")) {
            this.index = null;
            this.isDeleteAll = true;
        } else {
            try {
                this.index = Integer.parseInt(parts[1]) - 1;
                this.isDeleteAll = false;
            } catch (NumberFormatException e) {
                throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                        "Correct format for delete is `delete <task number>` or `delete all`");
            }
        }
    }

    /**
     * Executes the delete command and returns a response.
     *
     * @param tasks   The task list.
     * @param storage The storage handler.
     * @return A message confirming the deletion.
     * @throws LunaException If an invalid task number is provided.
     */
    @Override
    public String executeAndReturn(TaskList tasks, Storage storage) throws LunaException {
        if (isDeleteAll) {
            if (tasks.getTasks().isEmpty()) {
                throw new LunaException(LunaException.ErrorType.INVALID_FORMAT, "There are no tasks to delete!");
            }
            tasks.getTasks().clear();
            storage.saveTasks(tasks.getTasks());
            return "Oki~ I've removed all tasks!\nNow you have 0 tasks left in the list~";
        } else {
            Task removedTask = tasks.deleteTask(index);
            storage.saveTasks(tasks.getTasks());
            return "Oki~ I've removed this task!:\n" + removedTask
                           + "\nNow you have " + tasks.getTasks().size() + " tasks left in the list~";
        }
    }
}
