/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lorenzo Marietta
 */
public class EditDistance {

    public static int iterativeEditDistance(String s1, String s2) {
        int bestHit = 0;
        s1=s1.toLowerCase();
        s2=s2.toLowerCase();
        
        for (int i = 0; (i < s1.length()) && (bestHit <= s2.length()/2); i++) {
            int currentHit = 0;
            int lastHitIndex = -1;
            for (int j = 0, k = i; k < s1.length() && j < s2.length(); j++) {
                if (s1.charAt(k) == s2.charAt(j)) {
                    k++;
                    currentHit++;
                    lastHitIndex = j;
                } else if (lastHitIndex != -1 && j == s2.length() - 1 && i < s1.length()) {
                    k++;
                    j = lastHitIndex;
                }
            }
            if (bestHit < currentHit) {
                bestHit = currentHit;
            }
        }
        return ((s2.length() - bestHit) + (s1.length() - bestHit));
    }
    static int editDistanceDin(String s1, String s2){
        int m=s1.length();
        int n=s2.length();
        s1=s1.toLowerCase();
        s2=s2.toLowerCase();
        // Create a table to store results of subproblems
        int dp[][] = new int[m+1][n+1];
      
        // Fill d[][] in bottom up manner
        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                // If first string is empty, only option is to
                // isnert all characters of second string
                if (i==0)
                    dp[i][j] = j;  // Min. operations = j
      
                // If second string is empty, only option is to
                // remove all characters of second string
                else if (j==0)
                    dp[i][j] = i; // Min. operations = i
      
                // If last characters are same, ignore last char
                // and recur for remaining string
                else if (s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
      
                // If last character are different, consider all
                // possibilities and find minimum
                else
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j-1],dp[i-1][j]),dp[i-1][j-1]); 
            }
        }
  
        return dp[m][n];
    }
    public static int editDistance(String s1, String s2) {
        if (s1.isEmpty()) {
            return s2.length();
        } else if (s2.isEmpty()) {
            return s1.length();
        } else {
            int rem = editDistance(s1, rest(s2)) + 1;
            int add = editDistance(rest(s1), s2) + 1;
            int noope = editDistance(rest(s1), rest(s2)) + (s1.charAt(0) == s2.charAt(0) ? 0 : 2);
            return Math.min(Math.min(add, rem), noope);
        }
    }

    private static String rest(String s) {
        String result = "";
        for (int i = 1; i < s.length(); i++) {
            result = result + s.charAt(i);
        }
        return result;
    }
}
