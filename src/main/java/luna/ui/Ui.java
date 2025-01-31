package luna.ui;

import java.util.Scanner;

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
     * Closes the Scanner to free resources.
     */
    public void close() {
        sc.close();
    }
}
