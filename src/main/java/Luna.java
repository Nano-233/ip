import java.util.Scanner;  // Import the Scanner class

public class Luna {
  public static void main(String[] args) {
    String line = "____________________________________________________________\n";
    String logo = "     /\\_/\\  \n" +
        "    ( o.o )  \n" +
        "     > ❤ <    \n";
    String start = "Hello I'm Luna \nHow may I help?\n";
    String end = "Bye~\n";

    Task[] tasks = new Task[100]; //array of tasks
    int taskCount = 0;

    Scanner sc = new Scanner(System.in);

    System.out.print(line + logo + start + line); //start msg

    while (true) {
      String input = sc.nextLine().trim();

      if (input.equalsIgnoreCase("bye")) {
        break;

      } else if (input.equalsIgnoreCase("list")) {
        System.out.print(line);
        if (taskCount == 0) {
          System.out.println("Your list is empty.");
        } else {
          System.out.println("Here are the tasks in your list:");
          for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
          }
        }

      } else if (input.startsWith("mark ")) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index >= 0 && index < taskCount) {
          tasks[index].mark();
          System.out.print(line + "Nice! I've marked this task as done:\n");
          System.out.println(tasks[index] + "\n" + line);
        } else {
          System.out.print(line + "Invalid task number.\n" + line);
        }

      } else if (input.startsWith("unmark ")) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index >= 0 && index < taskCount) {
          tasks[index].unmark();
          System.out.print(line + "OK, I've marked this task as not done yet:\n");
          System.out.println(tasks[index] + "\n" + line);
        } else {
          System.out.print(line + "Invalid task number.\n" + line);
        }

      } else if (input.startsWith("todo ")) {
        String description = input.substring(5).trim();
        tasks[taskCount] = new Todo(description);
        taskCount++;
        System.out.print(line + "Got it. I've added this task:\n");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.\n" + line);

      } else if (input.startsWith("deadline ")) {
        String[] parts = input.substring(9).split("/by");
        String description = parts[0].trim();
        String by = parts[1].trim();
        tasks[taskCount] = new Deadline(description, by);
        taskCount++;
        System.out.print(line + "Got it. I've added this task:\n");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.\n" + line);

      } else if (input.startsWith("event ")) {
        String[] parts = input.substring(6).split("/from|/to");
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        tasks[taskCount] = new Event(description, from, to);
        taskCount++;
        System.out.print(line + "Got it. I've added this task:\n");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.\n" + line);

      } else {
        System.out.print(line + "I'm sorry, I don't understand that command.\n" + line);
      }
    }

    System.out.print(line + end + line); //end msg
    sc.close();
    System.exit(0);
  }
}
