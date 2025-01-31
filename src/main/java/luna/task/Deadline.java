package luna.task;

import luna.LunaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Deadline extends Task {
  protected LocalDate by;
  private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
  private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy", Locale.ENGLISH);

  public Deadline(String description, LocalDate by) {
    super(description);
    this.by = by;
  }

  public Deadline(String description, String by) throws LunaException {
    super(description);
    try {
      this.by = LocalDate.parse(by.trim(), INPUT_FORMATTER);
    } catch (DateTimeParseException e) {
      throw new LunaException(LunaException.ErrorType.INVALID_FORMAT, "Correct format for deadline is `deadline <description> /by <d/M/yyyy>`");
    }
  }

  @Override
  public String toString() {
    return "[D][" + getStatusIcon() + "] " + description + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
  }

  @Override
  public String toFileFormat() {
    return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(INPUT_FORMATTER);
  }
}
