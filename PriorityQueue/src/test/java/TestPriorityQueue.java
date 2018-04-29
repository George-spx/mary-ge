import java.util.ArrayList;
import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TestPriorityQueue{

  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Test
  public void testAddPriorityQueue() throws PriorityQueueException{
    Comparator <Integer> comparator = initializeIntegerComparator();
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(comparator); 
    priorityQueue.add(5);
    priorityQueue.add(4);
    priorityQueue.add(3);
    priorityQueue.add(6);
    priorityQueue.printHeap();
  }

  public Comparator<Integer> initializeIntegerComparator(){
    Comparator<Integer> comparator = new Comparator<Integer>(){
      @Override
      public int compare(Integer el1, Integer el2){
        return el1 - el2;
      }
    };  
    return comparator;
  }
}
