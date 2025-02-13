package luna.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import luna.LunaException;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    /**
     * Formatter for displaying date output in a readable format.
     */
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d 'of' MMMM yyyy", Locale.ENGLISH);

    /**
     * Formatter for parsing date input.
     */
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * Start date of the event.
     */
    protected LocalDate from;

    /**
     * End date of the event.
     */
    protected LocalDate to;

    /**
     * Constructs an event with a given description, start date, and end date.
     *
     * @param description Description of the event.
     * @param from        Start date of the event.
     * @param to          End date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an event with string input for dates.
     *
     * @param description Description of the event.
     * @param from        Start date as a string.
     * @param to          End date as a string.
     * @throws LunaException If date format is incorrect.
     */
    public Event(String description, String from, String to) throws LunaException {
        super(description);
        try {
            this.from = LocalDate.parse(from.trim(), INPUT_FORMATTER);
            this.to = LocalDate.parse(to.trim(), INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Correct format for event is `event <description> /from <d/M/yyyy> /to <d/M/yyyy>`");
        }
    }

    /**
     * Returns the string representation of the event.
     *
     * @return String format of the event task.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (from: "
                       + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")" + tagToString();
    }


    /**
     * Converts the event to a string format for file storage.
     *
     * @return Formatted string representation for saving to file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | "
                       + (tags.isEmpty() ? " " : String.join(",", tags)) + " | "
                       + description + " | " + from.format(INPUT_FORMATTER) + " | " + to.format(INPUT_FORMATTER);
    }

}
