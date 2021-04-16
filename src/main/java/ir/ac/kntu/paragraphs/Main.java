package ir.ac.kntu.paragraphs;
import static ir.ac.kntu.paragraphs.Algorithm.wordingWrap;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        System.out.println("enter number of words u want to enter : ");
        n = Integer.parseInt(scanner.next());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = "";
        }
        for (int i = 0; i < n; i++) {
            words[i] = scanner.next();
        }

        System.out.println("enter words : ");
        int[] wordsLen = new int[n];
        for (int i = 0; i < n; i++) {
            wordsLen[i] = words[i].length();
        }

        System.out.println("enter M :");
        int m = Integer.parseInt(scanner.next());

        wordingWrap(wordsLen, m, words);
    }
}
