package luna;

import luna.command.Command;
import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;


public class Luna {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

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

    public static void main(String[] args) {
        new Luna("data/Luna.txt").run();
    }
}
