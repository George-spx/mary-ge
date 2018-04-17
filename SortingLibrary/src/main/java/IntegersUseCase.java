import java.util.Comparator;
import java.util.ArrayList;
import java.io.*;

public class IntegersUseCase{

  public static boolean isArrayOrdered(ArrayList<Long> array){
    for(int i = 0; i < array.size()-1; i++){
      if(array.get(i) > array.get(i+1)){
        return false;
      }
    }
    return true;
  }

  public static void populateArray(ArrayList<Long> integers, BufferedReader readFile){
    System.out.print("Populating array...");
    long startTime = getTimeSeconds();
    String stringRead;
    try{
      while((stringRead = readFile.readLine()) != null){
      integers.add(Long.parseLong(stringRead));
      } 
    }catch(IOException e){
      System.out.println(e);
    }
    long endTime = getTimeSeconds();
    long duration = endTime - startTime;
    System.out.println();
    System.out.println("Array populated in: "+duration+"s");
  }

  public static BufferedReader initializeBufferedReader(String fileName){
    try{
      BufferedReader readFile = new BufferedReader(new FileReader(fileName));
      return readFile;
    }catch(FileNotFoundException e){
      System.out.println(e);
    }
    return null;
  }

  public static Comparator<Long> initializeLongComparator(){
    Comparator <Long> comparator = new Comparator<Long>(){
      @Override
      public int compare(Long e1, Long e2){
        long res = e1-e2;
        if(res < 0){
          return -1;
        }else if(res > 0){
          return 1;
        }else{
          return 0;
        }
      }
    };
    return comparator;
  }

  public static int userInput(){
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int choice = 0; 
    System.out.println();
    System.out.println("1 mergeSort");
    System.out.println("2 insertionSort");
    System.out.print("  Choice: "); 
    try{
      choice = Integer.parseInt(in.readLine());
    }catch(IOException e){
      System.out.println(e);
    }
    System.out.println();
    return choice;
  }

  public static long getTimeSeconds(){
    return System.currentTimeMillis()/1000; 
  }

  public static void execute(int choice, ArrayList<Long> integers, Comparator<Long> comparator){
    System.out.print("Ordering array...");
    long startTime = getTimeSeconds(); 
    try{
      if(choice == 1){
       Sort.<Long>mergeSort(integers, comparator);
      }else{
       Sort.<Long>insertionSort(integers, comparator);
      }
    }catch(SortException e){
      System.out.println(e);
    }
    long endTime = getTimeSeconds(); 
    long duration = endTime - startTime;
    System.out.println();
    System.out.println("Array ordered in: "+duration+"s");
  }

  public static void main(String[] args){
    Comparator<Long> comparator = initializeLongComparator(); 
    BufferedReader readFile = initializeBufferedReader("../resources/integers.csv");
    ArrayList<Long> integers = new ArrayList<Long>();
    populateArray(integers, readFile);
    int choice = userInput();
    execute(choice, integers, comparator);
  }
}
