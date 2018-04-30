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
    Comparator <QueueElement<String,Integer>> comparator = initializeIntegerComparator();
    PriorityQueue<String,Integer> priorityQueue = new PriorityQueue<String,Integer>(comparator); 
    ArrayList<String> expectedArray = new ArrayList<String>();
    ArrayList<String> resultedArray = new ArrayList<String>();
    expectedArray.add("Roberta");
    expectedArray.add("Francesco");
    expectedArray.add("Giulia");
    expectedArray.add("George");
    expectedArray.add("Maily");

    priorityQueue.push(new QueueElement("George", 4));
    priorityQueue.push(new QueueElement("Giulia", 7));
    priorityQueue.push(new QueueElement("Roberta", 12));
    priorityQueue.push(new QueueElement("Maily", 1));
    priorityQueue.push(new QueueElement("Francesco", 9));

    boolean stop = false;
    while(!stop){
      try{
        QueueElement<String,Integer> pop = priorityQueue.pop();
        resultedArray.add(pop.element);
      }catch(PriorityQueueException e){
        stop = true;
      }
    } 
    assertEquals(expectedArray, resultedArray);
  }
  
  @Test
  public void testPopOnEmptyQueue() throws PriorityQueueException{
    Comparator <QueueElement<String,Integer>> comparator = initializeIntegerComparator();
    PriorityQueue<String,Integer> priorityQueue = new PriorityQueue<String,Integer>(comparator); 
    thrown.expect(PriorityQueueException.class);
    thrown.expectMessage("Cannot pop, Queue is empty!");

    priorityQueue.pop();
  }

  @Test
  public void testAddNullElement() throws PriorityQueueException{
    Comparator <QueueElement<String,Integer>> comparator = initializeIntegerComparator();
    PriorityQueue<String,Integer> priorityQueue = new PriorityQueue<String,Integer>(comparator); 
    thrown.expect(PriorityQueueException.class);
    thrown.expectMessage("Element cannot be null");

    priorityQueue.push(null);
  }

  @Test
  public void testAddSamePriorityTwice() throws PriorityQueueException{
    Comparator <QueueElement<String,Integer>> comparator = initializeIntegerComparator();
    PriorityQueue<String,Integer> priorityQueue = new PriorityQueue<String,Integer>(comparator); 
    ArrayList<String> expectedArray = new ArrayList<String>();
    ArrayList<String> resultedArray = new ArrayList<String>();
    expectedArray.add("Roberta");
    expectedArray.add("Marco");
    expectedArray.add("Francesco");

    priorityQueue.push(new QueueElement("Roberta", 12));
    priorityQueue.push(new QueueElement("Francesco", 9));
    priorityQueue.push(new QueueElement("Marco", 12));

    boolean stop = false;
    while(!stop){
      try{
        QueueElement<String,Integer> pop = priorityQueue.pop();
        resultedArray.add(pop.element);
      }catch(PriorityQueueException e){
        stop = true;
      }
    } 
    assertEquals(expectedArray, resultedArray);
  }

  @Test
  public void testAddOnlySamePriority() throws PriorityQueueException{
    Comparator <QueueElement<String,Integer>> comparator = initializeIntegerComparator();
    PriorityQueue<String,Integer> priorityQueue = new PriorityQueue<String,Integer>(comparator); 
    ArrayList<String> expectedArray = new ArrayList<String>();
    ArrayList<String> resultedArray = new ArrayList<String>();
    expectedArray.add("Roberta");
    expectedArray.add("Francesco");
    expectedArray.add("Genna");
    expectedArray.add("Lucia");
    expectedArray.add("Marco");

    priorityQueue.push(new QueueElement("Roberta", 12));
    priorityQueue.push(new QueueElement("Francesco", 12));
    priorityQueue.push(new QueueElement("Genna", 12));
    priorityQueue.push(new QueueElement("Lucia", 12));
    priorityQueue.push(new QueueElement("Marco", 12));

    boolean stop = false;
    while(!stop){
      try{
        QueueElement<String,Integer> pop = priorityQueue.pop();
        resultedArray.add(pop.element);
      }catch(PriorityQueueException e){
        stop = true;
      }
    } 
    assertEquals(expectedArray, resultedArray);
  }

  @Test
  public void testAddHighestPriorityLast() throws PriorityQueueException{
    Comparator <QueueElement<String,Integer>> comparator = initializeIntegerComparator();
    PriorityQueue<String,Integer> priorityQueue = new PriorityQueue<String,Integer>(comparator); 
    ArrayList<String> expectedArray = new ArrayList<String>();
    ArrayList<String> resultedArray = new ArrayList<String>();
    expectedArray.add("Roberta");
    expectedArray.add("Francesco");
    expectedArray.add("Genna");
    expectedArray.add("Lucia");
    expectedArray.add("Marco");

    priorityQueue.push(new QueueElement("Francesco", 9));
    priorityQueue.push(new QueueElement("Genna", 2));
    priorityQueue.push(new QueueElement("Lucia", 2));
    priorityQueue.push(new QueueElement("Marco", 1));
    priorityQueue.push(new QueueElement("Roberta", 13));

    boolean stop = false;
    while(!stop){
      try{
        QueueElement<String,Integer> pop = priorityQueue.pop();
        resultedArray.add(pop.element);
      }catch(PriorityQueueException e){
        stop = true;
      }
    } 
    assertEquals(expectedArray, resultedArray);
  }

  public Comparator<QueueElement<String,Integer>> initializeIntegerComparator(){
    Comparator<QueueElement<String,Integer>> comparator = new Comparator<QueueElement<String,Integer>>(){
      @Override
      public int compare(QueueElement<String,Integer> el1, QueueElement<String,Integer> el2){
        if(el1.priority-el2.priority == 0){
          return el2.c - el1.c; 
        }else
        return el1.priority - el2.priority;
      }
    };  
    return comparator;
  }
}
