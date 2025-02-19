package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Represents a command to find tasks containing a keyword in the task list.
 */
public class FindCommand extends Command {
    private final String[] keywords;

    /**
     * Constructs a FindCommand object.
     *
     * @param input The input command string.
     * @throws LunaException If the input format is invalid.
     */
    public FindCommand(String input) throws LunaException {
        String[] parts = input.split(" ", 2);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Correct format for find is `find <keyword1> <keyword2> ...`~!");
        }

        this.keywords = parts[1].trim().toLowerCase().split(" ");
    }

    /**
     * Executes the find command and returns the search results.
     *
     * @param tasks   The task list.
     * @param storage The storage handler.
     * @return A message listing the found tasks.
     */
    @Override
    public String executeAndReturn(TaskList tasks, Storage storage) {
        return tasks.findTasks(keywords);
    }
}
