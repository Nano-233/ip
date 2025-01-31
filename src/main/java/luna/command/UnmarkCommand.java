package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param input User input containing the command and task number.
     * @throws LunaException If input is invalid.
     */
    public UnmarkCommand(String input) throws LunaException {
        try {
            String[] parts = input.split(" ");
            if (parts.length < 2) {
                throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                        "Correct format for unmark is `unmark <task number>`");
            }
            this.index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Correct format for unmark is `unmark <task number>`");
        }
    }

    /**
     * Executes the unmark command.
     *
     * @param tasks   Task list containing the task to be unmarked.
     * @param ui      User interface for displaying messages.
     * @param storage Storage handler for saving updated task list.
     * @throws LunaException If the task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LunaException {
        if (index < 0 || index >= tasks.getTasks().size()) {
            throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
        }

        tasks.unmarkTask(index);
        storage.saveTasks(tasks.getTasks());

        System.out.println("OKi~ I've marked this task as not done yet:");
        System.out.println(tasks.getTasks().get(index));
    }
}
