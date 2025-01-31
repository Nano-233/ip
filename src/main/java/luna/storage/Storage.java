package luna.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import luna.LunaException;
import luna.task.Task;

/**
 * Handles reading from and writing to the storage file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a Storage instance with the specified file path.
     *
     * @param filePath Path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks List of tasks to be saved.
     * @throws LunaException If an error occurs while saving the tasks.
     */
    public void saveTasks(ArrayList<Task> tasks) throws LunaException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();

            if (directory != null && !directory.exists()) {
                directory.mkdirs(); // Ensure all parent directories exist
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file into an ArrayList.
     *
     * @return List of tasks loaded from the file.
     * @throws LunaException If an error occurs while reading the file.
     */
    public ArrayList<Task> loadTasks() throws LunaException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT,
                    "Failed to load tasks: " + e.getMessage());
        }
        return tasks;
    }
}
