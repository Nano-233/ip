package luna;

import luna.command.Command;
import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;

/**
 * Represents the main class for Luna, a task management chatbot.
 * It initializes the necessary components and runs the command loop.
 */
public class Luna {
    /**
     * Handles file storage operations for tasks.
     */
    private Storage storage;

    /**
     * Manages the list of tasks.
     */
    private TaskList tasks;

    /**
     * Handles user interface interactions.
     */
    private Ui ui;

    /**
     * Parses user commands and converts them into executable commands.
     */
    private Parser parser;

    /**
     * Constructs a Luna instance.
     * Initializes UI, storage, and parser components, and loads tasks from storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Luna(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (LunaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main event loop of Luna.
     * Continuously reads user input, processes commands, and executes them until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (LunaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * The main entry point of Luna.
     * Creates an instance of Luna and starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Luna("data/Luna.txt").run();
    }
}
