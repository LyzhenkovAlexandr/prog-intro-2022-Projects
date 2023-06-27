import java.util.Scanner;

public class Contest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int n = Integer.parseInt(line);
        for (int i = 0; i < n; i++) {
            System.out.println(i * 710 - 25000 * 710);
        }
    }
}