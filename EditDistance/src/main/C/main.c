#include<stdio.h>
#include <string.h>
#include<stdlib.h>
#include <ctype.h>
#include "EditDistance.c"
int n = 0;

char **getWords(FILE *toCorrect){
  char **strings = (char**)malloc(sizeof(char*)*200);
  char readChar;
  int j = 0;
  for(int i = 0; i < 200; i++){
    strings[i] = (char*)malloc(sizeof(char)*50);
  }
  while(!feof(toCorrect)){
    fscanf(toCorrect, "%c", &readChar);
    if(readChar != ' ' && (readChar >= 'A' && readChar <= 'Z') || (readChar >= 'a' && readChar <= 'z')){
      strings[n][j] = readChar; 
      j++;
    }else{
      n++;
      j = 0; 
    }
  }
  return strings;
}

char **allocate(int a, int b){
  char **toReturn = (char **)malloc(sizeof(char*)*a);
  for(int i = 0; i < a; i++){
    toReturn[i] = (char*)malloc(sizeof(char)*b);
  }
  return toReturn;
}

void writeToFile(FILE *corrected, char* string, char **bestStrings, int n){
  fprintf(corrected, "%s (", string);
  for(int i = 0; i < n; i++){
    printf("StringCorrected %s\n", bestStrings[i]);
    fprintf(corrected, "%s, ", bestStrings[i]);
  }
  fprintf(corrected, ")\n");
}

void startCorrection(FILE *dictionary, FILE *toCorrect, FILE *corrected){
  char *stringDictionary = (char*)malloc(sizeof(char) * 80);
  char **stringsToCorrect = getWords(toCorrect);
  char **bestStrings = allocate(30, 50); 
  int n_best_strings = 0;
  int thisDistance;
  for(int i = 0; i < n-1 ; i++){
    int min = 400;
    while(!feof(dictionary)){
      fscanf(dictionary, "%s", stringDictionary);
      thisDistance = iterativeEditDistance(stringsToCorrect[i], stringDictionary); 
      if(thisDistance <= min){
        if(thisDistance == min){
          bestStrings[n_best_strings] = stringDictionary;
          n_best_strings++;
        }else{
          n_best_strings = 0;
          bestStrings[n_best_strings] = stringDictionary;
          n_best_strings++;
          min = thisDistance;
        }
      }
      }
    writeToFile(corrected, stringsToCorrect[i], bestStrings, n_best_strings);
    n_best_strings = 0;
    fseek(dictionary, 0, 0);
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
