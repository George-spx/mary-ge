#include<stdio.h>
#include <string.h>
#include<stdlib.h>
#include <ctype.h>
#define min(x,y) (x < y ? x : y)

/**
 *
 * @author Lorenzo Marietta
 * @author George Andrei Varga
 */

   int iterativeEditDistance(char* s1, char* s2) {
        int bestHit = 0;
       // s1=s1.toLowerCase();
        //s2=s2.toLowerCase();
       int lS1 = strlen(s1); 
       int lS2 = strlen(s2); 
        for (int i = 0; (i < lS1) && (bestHit <= lS2/2); i++) {
            int currentHit = 0;
            int lastHitIndex = -1;
            for (int j = 0, k = i; k < lS1 && j < lS2; j++) {
                if (s1[k] == s2[j]) {
                    k++;
                    currentHit++;
                    lastHitIndex = j;
                } else if (lastHitIndex != -1 && j == (lS2 - 1) && (i < lS1)) {
                    k++;
                    j = lastHitIndex;
                }
            }
            if (bestHit < currentHit) {
                bestHit = currentHit;
            }
        }
        return ((lS2 - bestHit) + (lS1 - bestHit));
    }

  char* rest(char* s) {
      char* result = (char*)malloc(sizeof(char)*strlen(s));
      int k = 0;
      for (int i = 1; i < strlen(s); i++) {
        result[k] =  s[i];
        k++;
      }
      return result;
    }

    int stringDistance(char* s1, char* s2){
      if(s1 == NULL || s2 == NULL){
        printf("Strings cannot be null");
        exit(1);
      }
        if (strlen(s1) == 0) {
            return strlen(s2);
        } else if (strlen(s2) == 0) {
            return strlen(s1);
        } else {
            int rem = (stringDistance(s1, rest(s2))) + 1;
            int add = (stringDistance((rest(s1)), s2)) + 1;
            int noope = stringDistance(rest(s1), (rest(s2))) + (s1[0] == s2[0] ? 0 : 2);
            return min(min(add, rem), noope);
        }
    }
