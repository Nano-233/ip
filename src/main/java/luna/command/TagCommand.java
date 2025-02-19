package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Handles tagging tasks in the task list.
 */
public class TagCommand extends Command {
    private final int index;
    private final String tag;

    /**
     * Constructs a TagCommand.
     *
     * @param input User input containing the command, task number, and tag.
     * @throws LunaException If the input format is invalid.
     */
    public TagCommand(String input) throws LunaException {
        try {
            String[] parts = input.split(" ", 3);
            if (parts.length < 3 || parts[2].trim().isEmpty()) {
                throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                        "Correct format for tagging is `tag <task number> <tag>`!");
            }
            this.index = Integer.parseInt(parts[1]) - 1;
            this.tag = parts[2].trim();
        } catch (NumberFormatException e) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Correct format for tagging is `tag <task number> <tag>`!!");
        }
    }

    @Override
    public String executeAndReturn(TaskList tasks, Storage storage) throws LunaException {
        tasks.addTag(index, tag);
        storage.saveTasks(tasks.getTasks());
        return "Oki~ I've added the tag `" + tag + "` to this task:\n" + tasks.getTasks().get(index);
    }
}
