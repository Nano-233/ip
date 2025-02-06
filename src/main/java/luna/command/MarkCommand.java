package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Handles marking tasks as done.
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
            this.index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Correct format for mark is `mark <task number>`");
        }
    }

    @Override
    public String executeAndReturn(TaskList tasks, Storage storage) throws LunaException {
        tasks.markTask(index);
        storage.saveTasks(tasks.getTasks());
        return "Yay!~ I've marked this task as done:\n" + tasks.getTasks().get(index);
    }
}
