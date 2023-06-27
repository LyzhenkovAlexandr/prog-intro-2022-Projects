import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Character.DASH_PUNCTUATION;

public class Wspp {
    public static void word(StringBuilder s, Map<String, List<Integer>> map, int count_word) {
        String line = String.valueOf(s);
        if (map.containsKey(line)) {
            map.get(line).add(count_word);
        } else {
            List<Integer> pos = new ArrayList<>();
            pos.add(count_word);
            map.put(line, pos);
        }
    }

    public static void main(String[] args) {
        try {
//            FileInputStream file = new FileInputStream("D://Project IDEA//Test//src//input1.txt");
            FileInputStream file = new FileInputStream(args[0]);
            Scanner reader = new Scanner(file);
            StringBuilder s = new StringBuilder();
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            int count_word = 0;
            try {
                while (reader.hasNextLine()) {
                    Scanner str = new Scanner(reader.nextLine());

                    while (str.hasNextWord()) {
                        String word = str.nextWord();

                        for (int i = 0; i < word.length(); i++) {
                            char ch = word.charAt(i);
                            if (Character.isLetter(ch) || Character.toString(ch).equals("'") || Character.getType(ch) == DASH_PUNCTUATION) {
                                s.append(Character.toLowerCase(ch));
                                if (i == word.length() - 1) {
                                    count_word++;
                                    word(s, map, count_word);
                                    s = new StringBuilder();
                                }
                            } else if (s.length() != 0) {
                                count_word++;
                                word(s, map, count_word);
                                s = new StringBuilder();
                            }
                        }
                    }
                }
            } finally {
                reader.close();
            }
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8));
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D://Project IDEA//Test//src//output.txt"), StandardCharsets.UTF_8));
            try {
                for (Map.Entry<String, List<Integer>> item : map.entrySet()) {
                    out.write(item.getKey() + " " + item.getValue().size() + " ");
                    List<Integer> arr = item.getValue();
                    for (int i = 0; i < arr.size(); i++) {
                        if (i == arr.size() - 1)
                            out.write(String.valueOf(arr.get(i)));
                        else
                            out.write(arr.get(i) + " ");
                    }
                    out.newLine();
                }
            } finally {
                out.close();
            }
        } catch (IllegalStateException e) {
            System.out.println("IllegalStateException" + e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException" + e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException" + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("SecurityException" + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException" + e.getMessage());
        }
    }
}