package luna.ui;

import java.util.List;
import java.util.Scanner;

import luna.task.Task;

/**
 * Handles user interaction by displaying messages and reading user input.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructs a Ui object with a new Scanner for reading user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("     /\\_/\\  ");
        System.out.println("    ( o.o )  ");
        System.out.println("     > <3 <    ");
        System.out.println("Hello I'm luna.Luna ");
        System.out.println("How may I help?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a horizontal line separator for output formatting.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message indicating a failure to load tasks from file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Displays a given error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user as a string.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the results of a task search.
     *
     * @param matchingTasks The list of tasks that match the search keyword.
     */
    public void showFindResults(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }


    /**
     * Closes the Scanner to free resources.
     */
    public void close() {
        sc.close();
    }
}
