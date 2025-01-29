import java.io.*;
import java.util.ArrayList;

public class LunaStorage {
  private static final String FILE_PATH = "./data/Luna.txt";

  public static void saveTasks(ArrayList<Task> tasks) {
    try {
      File directory = new File("./data");
      if (!directory.exists()) {
        directory.mkdir();
      }

      BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
      for (Task task : tasks) {
        writer.write(task.toFileFormat() + "\n");
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("Failed to save tasks.");
    }
  }

  public static ArrayList<Task> loadTasks() {
    ArrayList<Task> tasks = new ArrayList<>();
    try {
      File file = new File(FILE_PATH);
      if (!file.exists()) {
        return tasks;
      }
      BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
      String line;
      while ((line = reader.readLine()) != null) {
        tasks.add(Task.fromFileFormat(line));
      }
      reader.close();
    } catch (IOException e) {
      System.out.println("Failed to load tasks.");
    }
    return tasks;
  }
}
