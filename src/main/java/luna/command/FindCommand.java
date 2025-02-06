package luna.command;

import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Represents a command to find tasks containing a keyword in the task list.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param input The input command string.
     */
    public FindCommand(String input) {
        this.keyword = input.substring(5).trim();
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
        return tasks.findTasks(keyword);
    }
}
