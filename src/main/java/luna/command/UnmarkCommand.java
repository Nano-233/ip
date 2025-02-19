package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Handles unmarking tasks as not done.
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
            this.index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Correct format for unmark is `unmark <task number>`!!");
        }
    }

    @Override
    public String executeAndReturn(TaskList tasks, Storage storage) throws LunaException {
        tasks.unmarkTask(index);
        storage.saveTasks(tasks.getTasks());
        return "OKi~ I've marked this task as not done yet:\n" + tasks.getTasks().get(index);
    }
}
