import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;

public class SumsApp{


  public static void populateArray(BufferedReader readFileInteger, ArrayList <Long> A){
    String read;
    try{
      while((read = readFileInteger.readLine()) != null){
        A.add(Long.parseLong(read));
      } 
    }catch(IOException e){
      System.out.println(e);
    }
  }

  public static void execApp(BufferedReader readFileSums, ArrayList<Long> A){
    String read;
    try{
      while((read = readFileSums.readLine()) != null){
        System.out.println(Sum.sumFinder(Long.parseLong(read), A));
      } 
      }catch(IOException e){
        System.out.println(e);
    }

  }
  
  public static void orderArray(ArrayList<Long> A){
    Comparator <Long> comparator = new Comparator<Long>(){
      @Override
      public int compare(Long e1, Long e2){
        long res = e1 - e2;
        if(e1 < e2){
          return -1;
        }else if(e1 > e2){
          return 1;
        }else return 0;
      }
    };

    MergeSorter <Long> mergeSorter = new MergeSorter(comparator, A);
    try{
      mergeSorter.increasingSort();
    }catch(SortException e){
      System.out.println(e);
    }
  }

  public static void main(String[] args){
    try{
      BufferedReader readFileSums = new BufferedReader(new FileReader("../res/sums.txt"));
      BufferedReader readFileInteger = new BufferedReader(new FileReader("../res/integers.csv"));
      ArrayList<Long> A = new ArrayList<>();
      populateArray(readFileInteger, A);
      orderArray(A);
      execApp(readFileSums, A);
    }catch(FileNotFoundException e){
      System.out.println(e);
    }
      }

}
