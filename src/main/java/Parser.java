public class Parser {
  public Command parse(String userInput) throws LunaException {
    String[] parts = userInput.split(" ", 2);
    String commandWord = parts[0];

    switch (commandWord) {
      case "todo", "event", "deadline":
        return new AddCommand(userInput);
      case "list":
        return new ListCommand();
      case "mark":
        return new MarkCommand(userInput);
      case "unmark":
        return new UnmarkCommand(userInput);
      case "delete":
        return new DeleteCommand(userInput);
      case "bye":
        return new ExitCommand();
      default:
        throw new LunaException(LunaException.ErrorType.UNKNOWN_COMMAND, "");
    }
  }
}
