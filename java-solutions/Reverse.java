import java.io.IOException;
import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            try {
                int[] line_array = new int[10];
                int[] num_array = new int[10];
                int countNums = 0;
                int countLines = -1;


                while (sc.hasNextLine()) {
                    countLines += 1;
                    if (countLines >= line_array.length) {
                        line_array = Arrays.copyOf(line_array, line_array.length * 2);
                    }


                    Scanner str = new Scanner(sc.nextLine());
                    while (str.hasNextInt()) {
                        int number = str.nextInt();
                        num_array[countNums] = number;
                        countNums += 1;
                        if (countNums >= num_array.length) {
                            num_array = Arrays.copyOf(num_array, num_array.length * 2);
                        }
                        line_array[countLines] += 1;
                    }
                }

                for (int i = countLines; i > -1; i--) {
                    for (int j = 0; j < line_array[i]; j++) {
                        System.out.print(num_array[countNums - 1] + " ");
                        countNums -= 1;
                    }
                    System.out.println();
                }
            } finally {
                sc.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}