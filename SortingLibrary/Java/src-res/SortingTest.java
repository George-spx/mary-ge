import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.io.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class SortingTest{

  @Test
  public void testSortEmptyArrayList(){
    Comparator <Integer> comparator = initializeComparator();
    InsertionSort <Integer> testInsertionSort = new InsertionSort<Integer>(comparator, true);
    ArrayList <Integer> expectedArray = new ArrayList<Integer>();

    testInsertionSort.sort();

    assertEquals(expectedArray, testInsertionSort.array);
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testAddNullElement() throws SortException{
    Comparator <Integer> comparator = initializeComparator();
    InsertionSort <Integer> testInsertionSort = new InsertionSort<Integer>(comparator, true);

    thrown.expect(SortException.class);
    thrown.expectMessage("element cannot be null");
    testInsertionSort.add(null);
    
  }

  @Test
  public void testSwapMethod(){
    Comparator <Integer> comparator = initializeComparator(); 
    ArrayList <Integer> array = new ArrayList<Integer>();
    ArrayList<Integer> expectedArray = new ArrayList<Integer>(); 
    InsertionSort <Integer> testInsertionSort = new InsertionSort<Integer>(comparator, true);
    testInsertionSort.array.add(4);
    testInsertionSort.array.add(3);
    expectedArray.add(3);
    expectedArray.add(4);

    testInsertionSort.swap(0, 1);
    
    assertEquals(expectedArray, testInsertionSort.array); 
  
  }

  @Test
  public void testAddMethodOnEmptyArray() throws Exception{
    Comparator <Integer> comparator = initializeComparator(); 
    InsertionSort <Integer> testInsertionSort = new InsertionSort<Integer>(comparator, true);
    ArrayList <Integer> expectedArray = new ArrayList<>();

    testInsertionSort.add(3);

    expectedArray.add(3);
    assertEquals(expectedArray, testInsertionSort.array);
   
  }

<<<<<<< HEAD
  @Test(timeout = 600000) 
  public void testIntegerUseCaseCrescent(){
   Comparator<Long> comparator = initializeLongComparator(); 
   InsertionSort <Long> testInsertionSort = new InsertionSort<Long>(comparator, true);
   BufferedReader readFile = initializeBufferedReader("test.csv");
=======
  @Test(timeout = 1000) 
  public void testIntegerUseCase(){
   Comparator<Long> comparator = initializeLongComparator(); 
   InsertionSort <Long> testInsertionSort = new InsertionSort<Long>(comparator, true);
   BufferedReader readFile = initializeBufferedReader("integers.csv");
>>>>>>> eae6c5748ab01a5d51eb8e4477aee18c3ab5ed1b
   populateAndOrderArray(testInsertionSort, readFile);

   assertTrue(isArrayOrdered(testInsertionSort.array));
  }

  public boolean isArrayOrdered(ArrayList<Long> array){
    for(int i = 0; i < array.size()-1; i++){
      if(array.get(i) > array.get(i+1)){
        return false;
      }
    }
    return true;
  }
<<<<<<< HEAD

  @Test(timeout = 600000) 
  public void testIntegerUseCaseDecrescent(){
   Comparator<Long> comparator = initializeLongComparator(); 
   InsertionSort <Long> testInsertionSort = new InsertionSort<Long>(comparator, false);
   BufferedReader readFile = initializeBufferedReader("numeri.csv");
   populateAndOrderArray(testInsertionSort, readFile);

   assertTrue(isArrayDecrescentOrdered(testInsertionSort.array));
  }

  public boolean isArrayDecrescentOrdered(ArrayList<Long> array){
    for(int i = 0; i < array.size()-1; i++){
      if(array.get(i) < array.get(i+1)){
        return false;
      }
    }
    return true;
  }
=======
>>>>>>> eae6c5748ab01a5d51eb8e4477aee18c3ab5ed1b
  public void populateAndOrderArray(InsertionSort<Long> testInsertionSort, BufferedReader readFile){
    String stringRead;
    try{
      while((stringRead = readFile.readLine()) != null){
      testInsertionSort.add(Long.parseLong(stringRead));
      } 
    }catch(IOException|SortException e){
      System.out.println(e);
      fail();
    }
  }

  public BufferedReader initializeBufferedReader(String fileName){
    try{
      BufferedReader readFile = new BufferedReader(new FileReader(fileName));
      return readFile;
    }catch(FileNotFoundException e){
      System.out.println(e);
      fail();
    }
    return null;
  }

  public Comparator<Long> initializeLongComparator(){
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

  public Comparator<Integer> initializeComparator(){
    Comparator<Integer> comparator = new Comparator<Integer>(){
      @Override
      public int compare(Integer el1, Integer el2){
        return el1 - el2;
      }
    };  
    return comparator;
  }
}
