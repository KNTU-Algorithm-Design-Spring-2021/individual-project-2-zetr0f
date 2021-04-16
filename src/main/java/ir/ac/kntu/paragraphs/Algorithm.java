package ir.ac.kntu.paragraphs;

import java.util.Arrays;

public class Algorithm {

    public static void wordingWrap(int[] wordsLength, int m, String[] words) {
        int currentLenUsage;
        int cost;
        int[] dp = new int[wordsLength.length];
        int[] ans = new int[wordsLength.length];
        dp[wordsLength.length - 1] = 0;
        ans[wordsLength.length - 1] = wordsLength.length - 1;
        for (int i = wordsLength.length - 2; i >= 0; i--) {
            currentLenUsage = -1;
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < wordsLength.length; j++) {
                currentLenUsage += (wordsLength[j] + 1);
                if (currentLenUsage > m) {
                    break;
                }
                if (j == wordsLength.length - 1) {
                    cost = 0;
                } else {
                    cost = (m - currentLenUsage) * (m - currentLenUsage)  * (m - currentLenUsage)  +
                            dp[j + 1];
                }
                if (cost < dp[i]) {
                    dp[i] = cost;
                    ans[i] = j;
                }
            }
        }
        int indexOfWord = 0;
        int indexOfLine = 1;
        while (indexOfWord < wordsLength.length) {
            System.out.println("Line " + indexOfLine++ + " : "
                    + Arrays.toString(Arrays.copyOfRange(words, indexOfWord, ans[indexOfWord] + 1)));
            indexOfWord = ans[indexOfWord] + 1;
        }
        System.out.println("TOTAL COST : " + dp[0]);
    }

}
