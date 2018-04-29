import java.util.ArrayList;
import java.util.Comparator;
import java.io.*;
/**
 * @author George
 */

public class PriorityQueue<T>{
  private ArrayList<T> heapQueue = null;
  private Comparator <? super T> comparator;

  /**
   * instantiates the arrayList heapQueue and the comparator
   * @param comparator the comparator used to order the heap
   */
  public PriorityQueue(Comparator <? super T> comparator){
    this.comparator = comparator;
    this.heapQueue = new ArrayList<>();
  }

  /**
   * add new element to the queue
   * @param element to be added
   */
  public void add(T element)throws PriorityQueueException{
    if(element == null){
      throw new PriorityQueueException("Element cannot be null");
    }else{
      heapQueue.add(element);
      heapify(heapQueue.size()-1);
    }
  }
  
  public void printHeap(){
    for(int i = 0; i < heapQueue.size(); i++){
      System.out.println(heapQueue.get(i));
    }
  }
  /**
   * adjusts the array to be an heap assuming that it is an heap except for the index in input
   * @param index the index of the element from where we need to start to heapify
   */
  public void heapify(int index){
    int parentIndex = parentOf(index);
    if(comparator.compare(heapQueue.get(index), heapQueue.get(parentIndex)) <0){
      swap(index, parentIndex);
      heapify(index);
    }
  }

  /**
   * returns the parent of i
   * @param i
   * @return the parent of the index in input
   */
   private int parentOf(int i) {
     if (i % 2 == 1) {
       return i / 2;
     }
     return (i - 1) / 2;
   }

  /**
   * returns the right children of i
   * @param i
   * @return the right children of the index in input
   */
  private int rightOf(int i) {
    return 2 * i + 2;
  }

  /**
   * returns the left children of i
   * @param i
   * @return the left children of the index in input
   */
  private int leftOf(int i) {
    return 2 * i + 1;
  }

  private void swap(int p1, int p2){
    T temp = heapQueue.get(p1);
    heapQueue.set(p1, heapQueue.get(p2)); 
    heapQueue.set(p2, temp); 
  }

}

