import java.io.*;
import java.util.ArrayList;

public class Storage {
  private final String filePath;

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public void saveTasks(ArrayList<Task> tasks) {
    try {
      File directory = new File("./data");
      if (!directory.exists()) {
        directory.mkdir();
      }

      BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
      for (Task task : tasks) {
        writer.write(task.toFileFormat() + "\n");
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("Failed to save tasks.");
    }
  }

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
      throw new LunaException(LunaException.ErrorType.UNKNOWN_COMMAND, "Failed to load tasks.");
    }
    return tasks;
  }
}
