package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand.
     *
     * @param input User input containing the command and task number.
     * @throws LunaException If input is invalid.
     */
    public MarkCommand(String input) throws LunaException {
        try {
            String[] parts = input.split(" ");
            if (parts.length < 2) {
                throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                        "Correct format for mark is `mark <task number>`");
            }
            this.index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Correct format for mark is `mark <task number>`");
        }
    }

    /**
     * Executes the mark command.
     *
     * @param tasks   Task list containing the task to be marked.
     * @param ui      User interface for displaying messages.
     * @param storage Storage handler for saving updated task list.
     * @throws LunaException If the task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LunaException {
        if (index < 0 || index >= tasks.getTasks().size()) {
            throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
        }

        tasks.markTask(index);
        storage.saveTasks(tasks.getTasks());

        System.out.println("Yay!~ I've marked this task as done:");
        System.out.println(tasks.getTasks().get(index));
    }
}
