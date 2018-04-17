import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;

public class SumUseCase{

  /**
   * fills the array with data read from file
   * @param readFileInteger BufferedReader pointing to a file containing numbers
   * @param A ArrayList to fill
   */
  public static void populateArray(BufferedReader readFileInteger, ArrayList <Long> A){
    long startTime = getTimeSeconds();
    System.out.println();
    System.out.println("Populating array...");

    String read;
    try{
      while((read = readFileInteger.readLine()) != null){
        A.add(Long.parseLong(read));
      } 
    }catch(IOException e){
      System.out.println(e);
    }
    long endTime = getTimeSeconds();
    long duration = endTime - startTime;
    System.out.println("Array populated in: "+duration+"s");

  }
  /**
   * for each number in file checks if A contains two numbers that summed give the taken number 
   * and prints true/false
   * @param readFileSums BufferedReader pointing to the file containing sums
   * @param A ArrayList containing the numbers
   */
  public static void execApp(BufferedReader readFileSums, ArrayList<Long> A){
    long startTime = getTimeSeconds();
    System.out.println();
    System.out.println("Checking Sums...");
    String read;
    try{
      while((read = readFileSums.readLine()) != null){
        System.out.println(SearchSum.sumFinder(Long.parseLong(read), A));
      } 
      }catch(IOException e){
        System.out.println(e);
    }
    long endTime = getTimeSeconds();
    long duration = endTime - startTime;
    System.out.println("Sums checked in: "+duration+"s");

  }
  /**
   * Instantiates a comparator between Long
   * @return the previously instantiated comparator
   */
  public static Comparator<Long> getComparator(){
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
    return comparator;
  }
/**
 * orders the array using the mergeSort: complexity nlog(n)
 * @param A the ArrayList to order
 */
  public static void orderArray(ArrayList<Long> A){
    Comparator<Long> comparator = getComparator();
    long startTime = getTimeSeconds();
    System.out.println();
    System.out.println("Ordering array...");
    try{
      Sort.<Long>mergeSort(A, comparator);
    }catch(SortException e){
      System.out.println(e);
    }
    long endTime = getTimeSeconds();
    long duration = endTime - startTime;
    System.out.println("Array ordered in: "+duration+"s");
  }
  /**
   * gets the current time in milliseconds from javaVM
   * @return the present time in seconds
   */
  public static long getTimeSeconds(){
    return System.currentTimeMillis()/1000; 
  }

  public static void main(String[] args){
    try{
      BufferedReader readFileSums = new BufferedReader(new FileReader("../resources/sums.txt"));
      BufferedReader readFileInteger = new BufferedReader(new FileReader("../resources/integers.csv"));
      ArrayList<Long> A = new ArrayList<>();
      populateArray(readFileInteger, A);
      orderArray(A);
      execApp(readFileSums, A);
    }catch(FileNotFoundException e){
      System.out.println(e);
    }
  }
}
