import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Luna {

  // Nested ErrorType Enum
  public enum ErrorType {
    INVALID_FORMAT("Incorrect format :< "),
    INVALID_TASK_NUMBER("Check the task number again to make sure it's correct~"),
    UNKNOWN_COMMAND("Sorry :< I don't quite understand");

    private final String message;

    ErrorType(String message) {
      this.message = message;
    }

    public String getMessage(String specificMessage) {
      return message + specificMessage;
    }
  }

  public static void main(String[] args) {
    String line = "____________________________________________________________\n";
    String logo = "     /\\_/\\  \n" +
        "    ( o.o )  \n" +
        "     > <3 <    \n";
    String start = "Hello I'm Luna \nHow may I help?\n";
    String end = "Bye~\n";

    ArrayList<Task> tasks = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    System.out.print(line + logo + start + line); //start msg

    while (true) {
      String input = sc.nextLine().trim();

      try {
        if (input.equalsIgnoreCase("bye")) {
          break;

        } else if (input.equalsIgnoreCase("list")) {
          System.out.print(line);
          if (tasks.isEmpty()) {
            System.out.println("Your list is empty :<. Add something!");
          } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
              System.out.println((i + 1) + ". " + tasks.get(i));
            }
          }
          System.out.print(line);

        } else if (input.startsWith("mark")) {
          String remainingInput = input.length() > 4 ? input.substring(4).trim() : "";
          if (remainingInput.isEmpty()) {
            throw new LunaException(ErrorType.INVALID_FORMAT, "Correct format for mark is `mark <task number>`");
          }
          int index = Integer.parseInt(remainingInput) - 1;
          if (index < 0 || index >= tasks.size()) {
            throw new LunaException(ErrorType.INVALID_TASK_NUMBER, "");
          }
          tasks.get(index).mark();
          System.out.print(line + "Yay!~ I've marked this task as done:\n");
          System.out.print(tasks.get(index) + "\n" + line);

        } else if (input.startsWith("unmark")) {
          String remainingInput = input.length() > 6 ? input.substring(6).trim() : "";
          if (remainingInput.isEmpty()) {
            throw new LunaException(ErrorType.INVALID_FORMAT, "Correct format for unmark is `unmark <task number>`");
          }
          int index = Integer.parseInt(remainingInput) - 1;
          if (index < 0 || index >= tasks.size()) {
            throw new LunaException(ErrorType.INVALID_TASK_NUMBER, "");
          }
          tasks.get(index).unmark();
          System.out.print(line + "OKi~ I've marked this task as not done yet:\n");
          System.out.print(tasks.get(index) + "\n" + line);

        } else if (input.startsWith("todo")) {
          String description = input.length() > 4 ? input.substring(4).trim() : "";
          if (description.isEmpty()) {
            throw new LunaException(ErrorType.INVALID_FORMAT, "Correct format for todo is `todo <description>`");
          }
          tasks.add(new Todo(description));
          System.out.print(line + "Got it~ I've added this task:\n");
          System.out.println(tasks.get(tasks.size() - 1));
          System.out.print("Now you have " + tasks.size() + " tasks in the list!!\n" + line);

        } else if (input.startsWith("deadline")) {
          String remainingInput = input.length() > 8 ? input.substring(8).trim() : "";
          String[] parts = remainingInput.split("/by", 2);
          if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new LunaException(ErrorType.INVALID_FORMAT, "Correct format for deadline is `deadline <description> /by <time>`");
          }
          String description = parts[0].trim();
          String by = parts[1].trim();
          tasks.add(new Deadline(description, by));
          System.out.print(line + "Got it~ I've added this task:\n");
          System.out.println(tasks.get(tasks.size() - 1));
          System.out.print("Now you have " + tasks.size() + " tasks in the list!!\n" + line);

        } else if (input.startsWith("event")) {
          String remainingInput = input.length() > 5 ? input.substring(5).trim() : "";
          String[] parts = remainingInput.split("/from|/to", 3);
          if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new LunaException(ErrorType.INVALID_FORMAT, "Correct format for event is `event <description> /from <start> /to <end>`");
          }
          String description = parts[0].trim();
          String from = parts[1].trim();
          String to = parts[2].trim();
          tasks.add(new Event(description, from, to));
          System.out.print(line + "Got it~ I've added this task:\n");
          System.out.println(tasks.get(tasks.size() - 1));
          System.out.print("Now you have " + tasks.size() + " tasks in the list!!\n" + line);

        } else if (input.startsWith("delete")) {
          String remainingInput = input.length() > 6 ? input.substring(6).trim() : "";
          if (remainingInput.isEmpty()) {
            throw new LunaException(ErrorType.INVALID_FORMAT, "Correct format for delete is `delete <task number>`");
          }
          int index = Integer.parseInt(remainingInput) - 1;
          if (index < 0 || index >= tasks.size()) {
            throw new LunaException(ErrorType.INVALID_TASK_NUMBER, "");
          }
          Task removedTask = tasks.remove(index);
          System.out.print(line + "Oki~ I've removed this task!:\n");
          System.out.println(removedTask);
          System.out.print("Now you have " + tasks.size() + " tasks left in the list~\n" + line);

        } else {
          throw new LunaException(ErrorType.UNKNOWN_COMMAND, "");
        }

      } catch (LunaException e) {
        System.out.print(line + e.getMessage() + "\n" + line);
      } catch (NumberFormatException e) {
        System.out.print(line + "Invalid number format. Please enter a valid task number!\n" + line);
      }
    }

    System.out.print(line + end + line); //end msg
    sc.close();
    System.exit(0);
  }
}
