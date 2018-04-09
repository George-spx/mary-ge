import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;
import java.util.ArrayList;

public class SortingTest{

  @Test
  public void testSortNullArrayList(){
    Comparator comparator = initializeComparator();
    InsertionSort <Integer> testInsertionSort = new InsertionSort<Integer>(comparator, null);

    testInsertionSort.sort();

    assertEquals(null, testInsertionSort.array);
  }

  @Test
  public void testSwapMethod(){
    Comparator comparator = initializeComparator(); 
    ArrayList <Integer> array = new ArrayList<Integer>();
    array.add(4);
    array.add(3);
    InsertionSort <Integer> testInsertionSort = new InsertionSort<Integer>(comparator, array);
    ArrayList<Integer> expectedArray = new ArrayList<Integer>(); 
    expectedArray.add(3);
    expectedArray.add(4);

    testInsertionSort.swap(0, 1);
    
    assertEquals(expectedArray, testInsertionSort.array); 
  
  }

  @Test
  public void testAddMethodOnEmptyArray() throws Exception{
    Comparator comparator = initializeComparator(); 
    InsertionSort <Integer> testInsertionSort = new InsertionSort<Integer>(comparator);
    ArrayList <Integer> expectedArray = new ArrayList<>();

    testInsertionSort.add(3);

    expectedArray.add(3);
    assertEquals(expectedArray, testInsertionSort.array);
   
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
