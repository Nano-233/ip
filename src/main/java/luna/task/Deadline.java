package luna.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import luna.LunaException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    /**
     * Formatter for parsing date input.
     */
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * Formatter for displaying date output in a readable format.
     */

    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d 'of' MMMM yyyy", Locale.ENGLISH);

    /**
     * The due date of the task.
     */
    protected LocalDate by;

    /**
     * Constructs a deadline task with a given description and due date.
     *
     * @param description Description of the task.
     * @param by          The due date as a LocalDate object.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a deadline task with a string input for the due date.
     *
     * @param description Description of the task.
     * @param by          The due date as a string.
     * @throws LunaException If the date format is incorrect.
     */
    public Deadline(String description, String by) throws LunaException {
        super(description);
        try {
            this.by = LocalDate.parse(by.trim(), INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Correct format for deadline is `deadline <description> /by <d/M/yyyy>`");
        }
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return Formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Converts the deadline task to a string format for file storage.
     *
     * @return Formatted string representation for saving to file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(INPUT_FORMATTER);
    }
}
