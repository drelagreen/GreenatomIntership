import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String cmd;
        if (args.length!=0&&args[0].equalsIgnoreCase("prof"))
            FM.getInstance().setProfessionalMode();
        while (true) {
            System.out.print(">>");
            cmd = scanner.nextLine();
            FM.getInstance().todo(cmd);
        }
    }
}
