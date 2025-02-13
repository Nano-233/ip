package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.Deadline;
import luna.task.Event;
import luna.task.Task;
import luna.task.TaskList;
import luna.task.Todo;

/**
 * Handles adding tasks to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand based on user input.
     *
     * @param input The raw user input containing the task type and description.
     * @throws LunaException If the input format is invalid.
     */
    public AddCommand(String input) throws LunaException {
        String[] parts = input.split(" ", 2);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT, "Task description cannot be empty.");
        }

        String[] details = parts[1].split(" /by | /from | /to ", 3);
        this.task = createTask(parts[0], details);
    }

    /**
     * Creates a Task object based on the specified task type and details.
     *
     * @param taskType The type of task (e.g., "todo", "deadline", "event").
     * @param details  The task details, including description and date (if applicable).
     * @return A Task object of the appropriate type.
     * @throws LunaException If the task type is invalid or the details are incorrectly formatted.
     */
    private Task createTask(String taskType, String[] details) throws LunaException {
        switch (taskType.toLowerCase()) {
        case "todo" -> {
            validateDetails(details, 1, "todo <description>");
            return new Todo(details[0]);
        }
        case "deadline" -> {
            validateDetails(details, 2, "deadline <description> /by <dd/mm/yyyy>");
            return new Deadline(details[0], details[1]);
        }
        case "event" -> {
            validateDetails(details, 3, "event <description> /from <dd/mm/yyyy> /to <dd/mm/yyyy>");
            return new Event(details[0], details[1], details[2]);
        }
        default -> throw new LunaException(LunaException.ErrorType.UNKNOWN_COMMAND, "Invalid command.");
        }
    }

    /**
     * Validates that the provided details contain the required number of fields.
     *
     * @param details        The array of details extracted from user input.
     * @param requiredLength The minimum number of required details for the task type.
     * @param correctFormat  The correct format of the command, used in error messages.
     * @throws LunaException If the details array has missing or empty fields.
     */
    private void validateDetails(String[] details, int requiredLength, String correctFormat) throws LunaException {
        if (details.length < requiredLength || anyEmpty(details, requiredLength)) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT, "Correct format: `" + correctFormat + "`");
        }
    }

    /**
     * Checks whether any of the required details are empty.
     *
     * @param details The array of task details.
     * @param count   The number of fields to check for emptiness.
     * @return true if any required detail is empty, false otherwise.
     */
    private boolean anyEmpty(String[] details, int count) {
        for (int i = 0; i < count; i++) {
            if (details[i].trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String executeAndReturn(TaskList tasks, Storage storage) throws LunaException {
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        return "Got it~ I've added this task:\n" + task + "\nNow you have "
                       + tasks.getTasks().size() + " tasks in the list!!";
    }
}
