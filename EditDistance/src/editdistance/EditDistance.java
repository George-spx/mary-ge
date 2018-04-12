/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editdistance;

/**
 *
 * @author Lorenzo Marietta
 */
public class EditDistance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String s = "prosciutto";
        String ss = "proscutro";
        System.out.println(calculateDistance(s, ss));
    }

    public static int calculateDistance(String s1, String s2) {
        int bestHit = 0;
        for (int i = 0; (i < s1.length()) && (bestHit <= s2.length()); i++) {
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

}
