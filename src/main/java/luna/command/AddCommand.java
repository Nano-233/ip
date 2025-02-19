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
        this.task = createTask(parts[0], (parts.length > 1) ? parts[1] : "");
    }



    /**
     * Creates a Task object based on the specified task type and details.
     *
     * @param taskType The type of task (e.g., "todo", "deadline", "event").
     * @param details  The task details, including description and date (if applicable).
     * @return A Task object of the appropriate type.
     * @throws LunaException If the task type is invalid or the details are incorrectly formatted.
     */
    private Task createTask(String taskType, String details) throws LunaException {
        if (details.trim().isEmpty()) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Task description cannot be empty.\nCorrect format: `" + getFormat(taskType) + "`");
        }

        String[] splitDetails = details.split(" /by | /from | /to ", 3);

        return switch (taskType.toLowerCase()) {
            case "todo" -> {
                validateDetails(splitDetails, 1, "todo <description>");
                yield new Todo(splitDetails[0]);
            }
            case "deadline" -> {
                validateDetails(splitDetails, 2, "deadline <description> /by <dd/mm/yyyy>");
                yield new Deadline(splitDetails[0], splitDetails[1]);
            }
            case "event" -> {
                validateDetails(splitDetails, 3, "event <description> /from <dd/mm/yyyy> /to <dd/mm/yyyy>");
                yield new Event(splitDetails[0], splitDetails[1], splitDetails[2]);
            }
            default -> throw new LunaException(LunaException.ErrorType.UNKNOWN_COMMAND, "Invalid command.");
        };
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
        if (details.length < requiredLength) {
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

    /**
     * Returns the correct format string based on task type.
     *
     * @param taskType The task type ("todo", "deadline", "event").
     * @return The expected format string.
     */
    private String getFormat(String taskType) {
        return switch (taskType.toLowerCase()) {
            case "todo" -> "todo <description>";
            case "deadline" -> "deadline <description> /by <dd/mm/yyyy>";
            case "event" -> "event <description> /from <dd/mm/yyyy> /to <dd/mm/yyyy>";
            default -> "<unknown format>";
        };
    }
}
