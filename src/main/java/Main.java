
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Helper helper = new Helper();
        boolean end = true;
        while (end) {
            helper.getInputStations();
            System.out.println("\nContinue? : ");
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("NO")) end = false;
        }
    }


}
