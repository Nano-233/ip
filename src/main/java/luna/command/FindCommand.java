package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.Task;
import luna.task.TaskList;
import luna.ui.Ui;

import java.util.List;

/**
 * Represents a command to find tasks based on a keyword search.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with a given search keyword.
     *
     * @param input The user input containing the search keyword.
     * @throws LunaException If the keyword is missing.
     */
    public FindCommand(String input) throws LunaException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Correct format: `find <keyword>`");
        }
        this.keyword = parts[1].trim();
    }

    /**
     * Executes the find operation by searching for tasks containing the keyword.
     *
     * @param tasks   The task list to search in.
     * @param ui      The user interface to display results.
     * @param storage The storage handler (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showFindResults(matchingTasks);
    }
}
