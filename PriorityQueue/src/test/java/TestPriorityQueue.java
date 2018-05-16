import java.util.ArrayList;
import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.io.*;

public class TestPriorityQueue{

  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Test
  public void testPriorityQueueCorrectness() throws PriorityQueueException{
    Comparator <QueueNode<Integer, String>> comparator = initializeIntegerComparator();
    PriorityQueue<Integer, String> priorityQueue = new PriorityQueue<Integer, String>(comparator); 
    ArrayList<String> expectedArray = new ArrayList<String>();
    ArrayList<String> resultedArray = new ArrayList<String>();
    expectedArray.add("Roberta");
    expectedArray.add("Francesco");
    expectedArray.add("Giulia");
    expectedArray.add("George");
    expectedArray.add("Maily");

    priorityQueue.enqueue(new QueueNode("George", 4));
    priorityQueue.enqueue(new QueueNode("Giulia", 7));
    priorityQueue.enqueue(new QueueNode("Roberta", 12));
    priorityQueue.enqueue(new QueueNode("Maily", 1));
    priorityQueue.enqueue(new QueueNode("Francesco", 9));

    boolean stop = false;
    while(!stop){
      try{
        QueueNode<Integer, String> dequeue = priorityQueue.dequeue();
        resultedArray.add(dequeue.value);
      }catch(PriorityQueueException e){
        stop = true;
      }
    } 
    assertEquals(expectedArray, resultedArray);
  }
  
  @Test
  public void testPopOnEmptyQueue() throws PriorityQueueException{
    Comparator <QueueNode<Integer, String>> comparator = initializeIntegerComparator();
    PriorityQueue<Integer, String> priorityQueue = new PriorityQueue<Integer, String>(comparator); 
    thrown.expect(PriorityQueueException.class);
    thrown.expectMessage("Cannot dequeue, Queue is empty!");

    priorityQueue.dequeue();
  }

  @Test
  public void testAddNullElement() throws PriorityQueueException{
    Comparator <QueueNode<Integer, String>> comparator = initializeIntegerComparator();
    PriorityQueue<Integer, String> priorityQueue = new PriorityQueue<Integer, String>(comparator); 
    thrown.expect(PriorityQueueException.class);
    thrown.expectMessage("Element cannot be null");

    priorityQueue.enqueue(null);
  }

  @Test
  public void testAddSamePriorityTwice() throws PriorityQueueException{
    Comparator <QueueNode<Integer, String>> comparator = initializeIntegerComparator();
    PriorityQueue<Integer, String> priorityQueue = new PriorityQueue<Integer, String>(comparator); 
    ArrayList<String> expectedArray = new ArrayList<String>();
    ArrayList<String> resultedArray = new ArrayList<String>();
    expectedArray.add("Roberta");
    expectedArray.add("Marco");
    expectedArray.add("Francesco");

    priorityQueue.enqueue(new QueueNode("Roberta", 12));
    priorityQueue.enqueue(new QueueNode("Francesco", 9));
    priorityQueue.enqueue(new QueueNode("Marco", 12));

    boolean stop = false;
    while(!stop){
      try{
        QueueNode<Integer, String> dequeue = priorityQueue.dequeue();
        resultedArray.add(dequeue.value);
      }catch(PriorityQueueException e){
        stop = true;
      }
    } 
    assertEquals(expectedArray, resultedArray);
  }

  @Test
  public void testAddOnlySamePriority() throws PriorityQueueException{
    Comparator <QueueNode<Integer, String>> comparator = initializeIntegerComparator();
    PriorityQueue<Integer, String> priorityQueue = new PriorityQueue<Integer, String>(comparator); 
    ArrayList<String> expectedArray = new ArrayList<String>();
    ArrayList<String> resultedArray = new ArrayList<String>();
    expectedArray.add("Roberta");
    expectedArray.add("Francesco");
    expectedArray.add("Genna");
    expectedArray.add("Lucia");
    expectedArray.add("Marco");

    priorityQueue.enqueue(new QueueNode("Roberta", 12));
    priorityQueue.enqueue(new QueueNode("Francesco", 12));
    priorityQueue.enqueue(new QueueNode("Genna", 12));
    priorityQueue.enqueue(new QueueNode("Lucia", 12));
    priorityQueue.enqueue(new QueueNode("Marco", 12));

    boolean stop = false;
    while(!stop){
      try{
        QueueNode<Integer, String> dequeue = priorityQueue.dequeue();
        resultedArray.add(dequeue.value);
      }catch(PriorityQueueException e){
        stop = true;
      }
    } 
    assertEquals(expectedArray, resultedArray);
  }

  @Test
  public void testAddHighestPriorityLast() throws PriorityQueueException{
    Comparator <QueueNode<Integer, String>> comparator = initializeIntegerComparator();
    PriorityQueue<Integer, String> priorityQueue = new PriorityQueue<Integer, String>(comparator); 
    ArrayList<String> expectedArray = new ArrayList<String>();
    ArrayList<String> resultedArray = new ArrayList<String>();
    expectedArray.add("Roberta");
    expectedArray.add("Francesco");
    expectedArray.add("Genna");
    expectedArray.add("Lucia");
    expectedArray.add("Marco");

    priorityQueue.enqueue(new QueueNode("Francesco", 9));
    priorityQueue.enqueue(new QueueNode("Genna", 2));
    priorityQueue.enqueue(new QueueNode("Lucia", 2));
    priorityQueue.enqueue(new QueueNode("Marco", 1));
    priorityQueue.enqueue(new QueueNode("Roberta", 13));

    boolean stop = false;
    while(!stop){
      try{
        QueueNode<Integer, String> dequeue = priorityQueue.dequeue();
        resultedArray.add(dequeue.value);
      }catch(PriorityQueueException e){
        stop = true;
      }
    } 
    assertEquals(expectedArray, resultedArray);
  }

  public Comparator<QueueNode<Integer, String>> initializeIntegerComparator(){
    Comparator<QueueNode<Integer, String>> comparator = new Comparator<QueueNode<Integer, String>>(){
      @Override
      public int compare(QueueNode<Integer, String> el1, QueueNode<Integer, String> el2){
        return el1.key - el2.key;
      }
    };  
    return comparator;
  }
}
