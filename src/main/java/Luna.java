import java.util.Scanner;  // Import the Scanner class

public class Luna {
  public static void main(String[] args) {
    String line = "____________________________________________________________\n";
    String logo = "     /\\_/\\  \n" +
        "    ( o.o )  \n" +
        "     > ❤ <    \n";
    String start = "Hello I'm Luna \nHow may I help?\n";
    String end = "Bye~\n";

    String[] tasks = new String[100]; //array of tasks
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
          for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
          }
        }
        System.out.print(line);
      } else {
        tasks[taskCount] = input;
        taskCount++;
        System.out.print(line + "Added: \"" + input + "\"\n" + line);
      }
    }

    System.out.print(line + end + line); //end msg
    sc.close();
    System.exit(0);
  }
}
