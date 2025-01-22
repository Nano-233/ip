import java.util.Scanner;  // Import the Scanner class

public class Luna {
  public static void main(String[] args) {
    String line = "____________________________________________________________\n";
    String logo = "     /\\_/\\  \n" +
        "    ( o.o )  \n" +
        "     > ❤ <    \n";
    String start = "Hello I'm Luna \nHow may I help?\n";
    String end = "Bye~\n";

    Scanner sc = new Scanner(System.in);

    System.out.print(line + logo + start + line); //start msg

    while (true) {
      String input = sc.nextLine(); //reads input
      if (input.equalsIgnoreCase("bye")) {
        break;
      }
      System.out.print(line + input + "\n" + line); //echo
    }

    System.out.print(line + end + line); //end msg
    System.exit(0);
  }
}
