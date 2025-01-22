import java.util.Scanner;  // Import the Scanner class

public class Luna {
  public static void main(String[] args) {
    String line = "____________________________________________________________\n";
    String logo = "     /\\_/\\  \n" +
        "    ( o.o )  \n" +
        "     > <3 <    \n";
    String start = "Hello I'm Luna \nHow may I help?\n";
    String end = "Bye~\n";

    Task[] tasks = new Task[100]; //array of tasks
    int taskCount = 0;

    Scanner sc = new Scanner(System.in);

    System.out.print(line + logo + start + line); //start msg

    while (true) {
      String input = sc.nextLine().trim();

      try {
        if (input.equalsIgnoreCase("bye")) {
          break;

        } else if (input.equalsIgnoreCase("list")) {
          System.out.print(line);
          if (taskCount == 0) {
            System.out.println("Your list is empty :<. Add something!");
          } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
              System.out.println((i + 1) + ". " + tasks[i]);
            }
          }
          System.out.print(line);

        } else if (input.startsWith("mark ")) {
          int index = Integer.parseInt(input.split(" ")[1]) - 1;
          if (index < 0 || index >= taskCount) {
            throw new LunaException("Check the task number again to make sure it's correct~");
          }
          tasks[index].mark();
          System.out.print(line + "Yay!~ I've marked this task as done:\n");
          System.out.print(tasks[index] + "\n" + line);

        } else if (input.startsWith("unmark ")) {
          int index = Integer.parseInt(input.split(" ")[1]) - 1;
          if (index < 0 || index >= taskCount) {
            throw new LunaException("Check the task number again to make sure it's correct~");
          }
          tasks[index].unmark();
          System.out.print(line + "OKi~ I've marked this task as not done yet:\n");
          System.out.print(tasks[index] + "\n" + line);

        } else if (input.startsWith("todo")) {
          String description = input.length() > 4 ? input.substring(4).trim() : "";
          if (description.isEmpty()) {
            throw new LunaException("Incorrect format :< Correct format for todo is `todo <description>`");
          }
          tasks[taskCount] = new Todo(description);
          taskCount++;
          System.out.print(line + "Got it~ I've added this task:\n");
          System.out.println(tasks[taskCount - 1]);
          System.out.print("Now you have " + taskCount + " tasks in the list!!.\n" + line);

        } else if (input.startsWith("deadline")) {
          String remainingInput = input.length() > 8 ? input.substring(8).trim() : "";
          String[] parts = remainingInput.split("/by", 2);
          if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new LunaException("Incorrect format :< Correct format for deadline is `deadline <description> /by <time>`");
          }
          String description = parts[0].trim();
          String by = parts[1].trim();
          tasks[taskCount] = new Deadline(description, by);
          taskCount++;
          System.out.print(line + "Got it~ I've added this task:\n");
          System.out.println(tasks[taskCount - 1]);
          System.out.print("Now you have " + taskCount + " tasks in the list!!.\n" + line);

        } else if (input.startsWith("event")) {
          String remainingInput = input.length() > 5 ? input.substring(5).trim() : "";
          String[] parts = remainingInput.split("/from|/to", 3);
          if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new LunaException("Incorrect format :< Correct format for event is `event <description> /from <start> /to <end>`");
          }
          String description = parts[0].trim();
          String from = parts[1].trim();
          String to = parts[2].trim();
          tasks[taskCount] = new Event(description, from, to);
          taskCount++;
          System.out.print(line + "Got it~ I've added this task:\n");
          System.out.println(tasks[taskCount - 1]);
          System.out.print("Now you have " + taskCount + " tasks in the list!!.\n" + line);

        } else {
          throw new LunaException("Sorry :< I don't quite understand");
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
