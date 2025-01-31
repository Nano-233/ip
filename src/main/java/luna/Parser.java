package luna;

import luna.command.AddCommand;
import luna.command.Command;
import luna.command.DeleteCommand;
import luna.command.ExitCommand;
import luna.command.ListCommand;
import luna.command.MarkCommand;
import luna.command.UnmarkCommand;

/**
 * Parses user input and returns the appropriate command for execution.
 * This class processes raw user input and translates it into corresponding command objects.
 */
public class Parser {

    /**
     * Parses the given user input and returns a command to execute.
     *
     * @param userInput The raw input provided by the user.
     * @return The corresponding command object.
     * @throws LunaException If the input does not match any recognized commands.
     */
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
            throw new LunaException(
                    LunaException.ErrorType.UNKNOWN_COMMAND,
                    ""
            );
        }
    }
}
