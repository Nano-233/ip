package luna.ui;

import java.util.Scanner;

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

    public void close() {
        sc.close();
    }
}
