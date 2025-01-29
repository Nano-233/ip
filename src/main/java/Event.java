import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Event extends Task {
  protected LocalDate from;
  protected LocalDate to;
  private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
  private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy", Locale.ENGLISH);

  public Event(String description, LocalDate from, LocalDate to) {
    super(description);
    this.from = from;
    this.to = to;
  }

  public Event(String description, String from, String to) throws LunaException {
    super(description);
    try {
      this.from = LocalDate.parse(from.trim(), INPUT_FORMATTER);
      this.to = LocalDate.parse(to.trim(), INPUT_FORMATTER);
    } catch (DateTimeParseException e) {
      throw new LunaException(Luna.ErrorType.INVALID_FORMAT, "Correct format for event is `event <description> /from <d/M/yyyy> /to <d/M/yyyy>`");
    }
  }

  @Override
  public String toString() {
    return "[E][" + getStatusIcon() + "] " + description + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
  }

  @Override
  public String toFileFormat() {
    return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(INPUT_FORMATTER) + " | " + to.format(INPUT_FORMATTER);
  }
}
