package ir.ac.kntu.jamebond;

import java.net.MalformedURLException;
import java.util.*;

public class Algorithm {

    public static boolean canSplit(String s) throws MalformedURLException {
        Validator validator = new Validator("http://andrew.cmu.edu/course/15-121/dictionary.txt");
        if(s == null || s.length() == 0) return false;
        boolean[] table  = new boolean[s.length()+1];
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


    public static List<String> findWords(String s, Validator validator ,  Map<String, List<String>> map) {
        if(map.containsKey(s)){
            return map.get(s);
        }

        List<String> result = new ArrayList<String>();
        if (validator.valid(s)){
            result.add(s);
        }

        for(int i=1; i<=s.length(); i++) {
            String prefix = s.substring(0, i);
            if(validator.valid(prefix)){
                List<String> returnStringsList = findWords(s.substring(i), validator, map);
                for(String returnString :returnStringsList ){
                    result.add(prefix + " " + returnString);
                }
            }
        }
        map.put(s,result);
        return result;
    }

    public static void main(String[] args) throws MalformedURLException {
        System.out.println("enter ur string to check :");
        Validator validator = new Validator("http://andrew.cmu.edu/course/15-121/dictionary.txt");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (canSplit(input)) {
            Map<String, List<String>> map = new HashMap<>();
            System.out.println(findWords(input, validator, map));
        } else {
            System.out.println("it cant split to words");
        }
    }
}
