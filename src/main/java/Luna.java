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

      } else {
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.print(line + "Added: \"" + input + "\"\n" + line);
      }
    }

    System.out.print(line + end + line); //end msg
    sc.close();
    System.exit(0);
  }
}
