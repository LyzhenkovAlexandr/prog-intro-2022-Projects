import java.lang.Character;
public class Sum {
    public static void main(String[] args) {
        int sum = 0;

        for (String arg : args) {
            char[] char_string = arg.toCharArray();
            String num = "";
            for (int i = 0; i < char_string.length; i++) {
                if (!Character.isWhitespace(char_string[i])) {
                    if (i < char_string.length - 1) {
                        num += char_string[i];
                    } else if (i == char_string.length - 1) {
                        num += char_string[i];
                        sum += Integer.parseInt(num);
                        num = "";
                    }
                } else if (!num.equals("")) {
                    sum += Integer.parseInt(num);
                    num = "";
                }
            }
        }
        System.out.println(sum);
    }
}







