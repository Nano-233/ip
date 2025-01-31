package luna.task;

import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            System.out.println("Skipping corrupted task (invalid format): " + line);
            return null;
        }

        Task task = null;
        try {
            switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                if (parts.length < 4) {
                    System.out.println("Skipping corrupted deadline task (missing date): " + line);
                    return null;
                }
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                if (parts.length < 5) {
                    System.out.println("Skipping corrupted event task (missing dates): " + line);
                    return null;
                }
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                System.out.println("Skipping unknown task type: " + line);
                return null;
            }

            if ("1".equals(parts[1])) {
                task.mark();
            }

            System.out.println("Loaded task: " + task); // Debugging log

            return task;
        } catch (Exception e) {
            System.out.println("Skipping corrupted task: " + line);
            return null;
        }
    }
}
