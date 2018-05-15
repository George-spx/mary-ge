#include<stdio.h>
#include <string.h>
#include<stdlib.h>
#include <ctype.h>
#include "EditDistance.c"
int n = 0;
int d = 0;
char **allocate(int a, int b){
  char **toReturn = (char **)malloc(sizeof(char*)*a);
  for(int i = 0; i < a; i++){
    toReturn[i] = (char*)malloc(sizeof(char)*b);
  }
  return toReturn;
}

char **getWords(FILE *toCorrect){
  char **strings = allocate(200,50);
  char readChar;
  int j = 0;
  while(!feof(toCorrect)){
    fscanf(toCorrect, "%c", &readChar);
    if((readChar >= 'A' && readChar <= 'Z') || (readChar >= 'a' && readChar <= 'z')){
      strings[n][j] = readChar; 
      j++;
    }else{
      n++;
      j = 0; 
    }
  }
  return strings;
}

char **getDictionary(FILE *dictionary){
  char **strings = allocate(663000, 30);  
  char readChar;
  int j = 0;
  while(!feof(dictionary)){
    fscanf(dictionary, "%c", &readChar);
    if((readChar >= 'A' && readChar <= 'Z') || (readChar >= 'a' && readChar <= 'z')){
      strings[d][j] = readChar; 
      j++;
    }else{
      d++;
      j = 0; 
    }
  }
  return strings;

}


void writeToFile(FILE *corrected, char* string, char **bestStrings, int k){
  fprintf(corrected, "%s (", string);
  for(int i = 0; i < k; i++){
    fprintf(corrected, "%s, ", bestStrings[i]);
  }
  fprintf(corrected, ")\n");
}

void startCorrection(FILE *dictionary, FILE *toCorrect, FILE *corrected){
  char **stringsDictionary = getDictionary(dictionary);
  char **stringsToCorrect = getWords(toCorrect);
  char **bestStrings = allocate(30, 50); 
  int n_best_strings = 0;
  int thisDistance;
  for(int i = 0; i <= n ; i++){
    int min = 400;
    for(int j = 0; j <= d; j++){
      fscanf(dictionary, "%s", stringsDictionary[j]);
      thisDistance = iterativeEditDistance(stringsToCorrect[i], stringsDictionary[j]); 
      if(thisDistance <= min){
        if(thisDistance == min){
          bestStrings[n_best_strings] = stringsDictionary[j];
          n_best_strings++;
        }else if(thisDistance < min){
          n_best_strings = 0;
          bestStrings[n_best_strings] = stringsDictionary[j];
          n_best_strings++;
          min = thisDistance;
        }
      }
      }
    writeToFile(corrected, stringsToCorrect[i], bestStrings, n_best_strings);
    n_best_strings = 0;
  }
}

int main(){
  char* dictPath = "../resources/dictionary.txt";
  char* toCorrectPath = "../resources/correctme.txt";
  char* correctedPath = "../resources/corrected.txt";

  FILE *dictionary = fopen(dictPath, "r");
  FILE *toCorrect = fopen(toCorrectPath, "r");
  FILE *corrected = fopen(correctedPath, "w");

  if(dictionary == NULL){
    printf("file dictionary not found");
    exit(1);
  }
  if(toCorrect  == NULL){
    printf("file tocorrect not found");
    exit(1);
  }
  if(corrected == NULL){
    printf("File corrected not found");
    exit(1);
  }

  startCorrection(dictionary, toCorrect, corrected);
  fclose(dictionary);
  fclose(toCorrect);
  fclose(corrected);
}
