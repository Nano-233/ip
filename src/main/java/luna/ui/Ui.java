package luna.ui;

import java.util.List;
import java.util.Scanner;

import luna.task.Task;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("     /\\_/\\  ");
        System.out.println("    ( o.o )  ");
        System.out.println("     > <3 <    ");
        System.out.println("Hello I'm luna.Luna ");
        System.out.println("How may I help?");
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

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


    public void close() {
        sc.close();
    }
}
