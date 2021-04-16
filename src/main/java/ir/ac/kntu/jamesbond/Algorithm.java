package ir.ac.kntu.jamesbond;

import java.net.MalformedURLException;
import java.util.*;

public class Algorithm {
    public static Validator validator;

    static {
        try {
            validator = new Validator("C:\\Users\\ASUS\\Desktop\\algorithm design\\" +
                    "project2\\individual-project-2-zetr0f\\src\\main\\resources\\dic.txt");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static boolean canSplit(String s) {
        if (s == null || s.length() == 0) return false;
        boolean[] table = new boolean[s.length() + 1];
        table[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (table[j] && validator.valid(s.substring(j, i))) {
                    table[i] = true;
                }
            }
        }
        return table[s.length()];
    }


    public static List<String> findWords(String s, Validator validator, Map<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> result = new ArrayList<>();
        if (s.length() != 0 && validator.valid(s)) {
            result.add(s);
        }

        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if (validator.valid(prefix)) {
                List<String> returnStringsList = findWords(s.substring(i), validator, map);
                for (String returnString : returnStringsList) {
                    result.add(prefix + " " + returnString);
                }
            }
        }
        map.put(s, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("enter ur string to check :");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (canSplit(input)) {
            Map<String, List<String>> map = new HashMap<>();
            System.out.println(findWords(input, validator, map).toString());
        } else {
            System.out.println("it cant split to words");
        }
    }
}
