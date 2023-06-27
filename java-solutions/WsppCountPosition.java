//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import static java.lang.Character.DASH_PUNCTUATION;
//
//
//class CustomizedHashMap implements Comparator<Map.Entry<String, Integer>> {
//    @Override
//    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//        return -o1.getValue().compareTo(o2.getValue());
//    }
//}
//
//public class WsppCountPosition {
//    public static void add_map(StringBuilder s, Map<String, Integer> map) {
//        String word = s.toString();
//        if (map.containsKey(word)) {
//            map.put(word, map.get(word) + 1);
//        } else {
//            map.put(word, 1);
//        }
//    }
//    public static void add_map_arr(StringBuilder s, Map<String, String> map_arr, int count_line, int count_word) {
//        String word = s.toString();
//        s = new StringBuilder();
//        s.append(count_line);
//        s.append(":");
//        s.append(count_word);
//
//        if (map_arr.containsKey(word)) {
//            map_arr.put(word, map_arr.get(word) + " " + s);
//        } else {
//            map_arr.put(word, s.toString());
//        }
//    }
//
//
//    public static void main(String[] args) {
//        try {
////            FileInputStream file = new FileInputStream("D://Project IDEA//Test//src//input1.txt");
//            FileInputStream file = new FileInputStream(args[0]);
//            Scanner reader = new Scanner(file);
//            StringBuilder s = new StringBuilder();
//            Map<String, String> map_arr = new HashMap<>();
//            Map<String, Integer> map = new LinkedHashMap<>();
//            List<Map.Entry<String, Integer>> entries;
//            int count_line = 0;
//            try {
//                while (reader.hasNextLine()) {
//                    count_line++;
//                    int count_word = 0;
//                    Scanner str = new Scanner(reader.nextLine());
//
//                    while (str.hasNextWord()) {
//                        String word = str.nextWord();
//
//                        for (int i = 0; i < word.length(); i++) {
//                            char ch = word.charAt(i);
//                            if (Character.isLetter(ch) || Character.toString(ch).equals("'") || Character.getType(ch) == DASH_PUNCTUATION) {
//                                s.append(Character.toLowerCase(ch));
//                                if (i == word.length() - 1) {
//                                    count_word++;
//                                    add_map(s, map);
//                                    add_map_arr(s, map_arr, count_line, count_word);
//                                    s = new StringBuilder();
//                                }
//                            } else if (s.length() != 0) {
//                                count_word++;
//                                add_map(s, map);
//                                add_map_arr(s, map_arr, count_line, count_word);
//                                s = new StringBuilder();
//                            }
//                        }
//                    }
//                }
//                entries = new ArrayList<>(map.entrySet());
//                entries.sort(Map.Entry.comparingByValue());
//            } finally {
//                reader.close();
//            }
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8));
////            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D://Project IDEA//Test//src//output.txt"), StandardCharsets.UTF_8));
//            try {
//                for (Map.Entry<String, Integer> item : entries) {
//                    out.write(item.getKey() + " " + item.getValue() + " " + map_arr.get(item.getKey()));
//                    out.newLine();
//                }
//            } finally {
//                out.close();
//            }
//        } catch (IllegalStateException e) {
//            System.out.println("IllegalStateException" + e);
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("IndexOutOfBoundsException" + e);
//        } catch (NullPointerException e) {
//            System.out.println("NullPointerException" + e.getMessage());
//        } catch (SecurityException e) {
//            System.out.println("SecurityException" + e.getMessage());
//        } catch (FileNotFoundException e) {
//            System.out.println("FileNotFound" + e.getMessage());
//        } catch (IOException e) {
//            System.out.println("IOException" + e.getMessage());
//        }
//    }
//}


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


class CustomizedHashMap implements Comparator<Map.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o1.getValue() - o2.getValue();
    }
}

public class WsppCountPosition {
    public static void addMap(String s, Map<String, Integer> map) {
        // getOrDefault or get != null
        map.put(s, map.getOrDefault(s, 0) + 1);
    }

    public static void addMapArr(String s, Map<String, String> mapArr, int countLine, int countWord) {
        StringBuilder sb = new StringBuilder();
        sb.append(countLine);
        sb.append(":");
        sb.append(countWord);

        mapArr.computeIfPresent(s, (key, value) -> value + " " + sb);
        mapArr.putIfAbsent(s, sb.toString());
    }


    public static void main(String[] args) {
        try {
            Scanner reader = new Scanner(new FileInputStream(args[0]));
//            Scanner reader = new Scanner(new FileInputStream("D://Project IDEA//Test//src//input1.txt"));
            Map<String, String> mapArr = new HashMap<>();
            Map<String, Integer> map = new LinkedHashMap<>();
            List<Map.Entry<String, Integer>> entries;
            int countLine = 0;
            try {
                while (reader.hasNextLine()) {
                    countLine++;
                    int countWord = 0;
                    String line = reader.nextLine();
                    Scanner sc = new Scanner(line);
                    while (sc.hasNextTrueWord()) {
                        String s = sc.nextTrueWord();
                        countWord++;
                        addMap(s, map);
                        addMapArr(s, mapArr, countLine, countWord);
                    }
                }
                entries = new ArrayList<>(map.entrySet());
                entries.sort(new CustomizedHashMap());
            } finally {
                reader.close();
            }
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8));
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D://Project IDEA//Test//src//output.txt"), StandardCharsets.UTF_8));
            try {
                for (Map.Entry<String, Integer> item : entries) {
                    out.write(item.getKey() + " " + item.getValue() + " " + mapArr.get(item.getKey()));
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