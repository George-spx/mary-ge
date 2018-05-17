import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.io.*;

/**
 * @author George
 */
public class PriorityQueue <T,E>{
  private ArrayList<QueueNode<T,E>> heapQueue = null;
  private HashMap<T, Integer> nodePosition;
  private Comparator <QueueNode<T,E>> comparator;

  /**
   *
   * instantiates the arrayList heapQueue and the comparator
   * @param comparator the comparator used to order the heap
   */
  public PriorityQueue(Comparator <QueueNode<T,E>> comparator){
    this.comparator = comparator;
    this.heapQueue = new ArrayList<>();
    nodePosition = new HashMap<T, Integer>();
  }


  public ArrayList<QueueNode<T,E>> getQueue(){
    return this.heapQueue;
  }

  /**
   *
   * add new element to the queue
   * @param element the element to be added
   */
  public void enqueue(QueueNode<T,E> element)throws PriorityQueueException{
    if(element == null){
      throw new PriorityQueueException("Element cannot be null");
    }else{
      heapQueue.add(element);
      nodePosition.put(element.key, heapQueue.size()-1);
      up(heapQueue.size()-1);
    }
  }

  public void decreaseKey(T newKey, T oldKey) throws PriorityQueueException{
    if(nodePosition.containsKey(oldKey)){
      //if(comparator.compare(newKey,oldKey) < 0){
        int p = nodePosition.get(oldKey);
        heapQueue.get(p).key = newKey; 
        nodePosition.remove(oldKey);
        nodePosition.put(newKey, p);
        heapify(p);
      //}
      //else{
        //throw new PriorityQueueException("New key is bigger than old Key");
      //}
    }else{
      throw new PriorityQueueException("Key not found in queue");
    } 
  }

  /**
   *
   * returns and removes the element with the max priority from the queue
   * @return the element with max priority(root element)
   */
  public QueueNode dequeue() throws PriorityQueueException{
    if(heapQueue.size() == 0){
      throw new PriorityQueueException("Cannot extract, Queue is empty!");
    }
    QueueNode<T,E> topQueue = heapQueue.get(0);
    nodePosition.remove(topQueue.key);
    heapQueue.set(0, heapQueue.get(heapQueue.size()-1));
    heapQueue.remove(heapQueue.size()-1);
    heapify(0);
    return topQueue;
  }

  public void hashUpdateIndexes(int i1, int i2){
    nodePosition.remove(heapQueue.get(i2).key);
    nodePosition.remove(heapQueue.get(i1).key);

    nodePosition.put(heapQueue.get(i2).key, i2);
    nodePosition.put(heapQueue.get(i1).key, i1);
  }

  /**
   *
   * adjusts the array to be an heap assuming that it is an heap except for the element at index in input
   * @param index the index of the element from where we need to start to heapify
   */
  public void up(int index){
    int parentIndex = parentOf(index);
    if(parentIndex >= 0){
      if(comparator.compare(heapQueue.get(index), heapQueue.get(parentIndex)) > 0){
        swap(index, parentIndex);
        up(parentIndex);
      }
    }
  }
  
   
 /**
  *
  * returns the index of the element with max priority between the two in input
  * @param leftIndex the index of the left child
  * @param rightIndex the index of the right child
  * @return the index of the child with max priority of the two
  */ 
  int maxChild(int leftIndex, int rightIndex){
    if(rightIndex == -1 && leftIndex != -1) return leftIndex;
    else if(leftIndex == -1 && rightIndex != -1) return rightIndex;
    else if(leftIndex == -1 && rightIndex == -1) return -1;
    else
    return (comparator.compare(heapQueue.get(leftIndex), heapQueue.get(rightIndex)) > 0) ? leftIndex : rightIndex;
  }

  /**
   *
   * checks if the array represent correctly an heap
   * otherwise it transforms the array into a correct max-heap
   * @param index the index where it starts checking
   */ 
  public void heapify(int index){
    int lastIndex = heapQueue.size()-1;
    int rightIndex = rightOf(index);
    int leftIndex = leftOf(index);
    if(rightIndex <= lastIndex && leftIndex <= lastIndex){
      int maxChildIndex = maxChild(leftIndex, rightIndex);
      if(maxChildIndex != -1){
        if(comparator.compare(heapQueue.get(index), heapQueue.get(maxChildIndex)) < 0){
            swap(index, maxChildIndex);
            heapify(maxChildIndex);
        }
      }
    }
  }

  /**
   *
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
   *
   * returns the right children of i
   * @param i
   * @return the right children of the index in input
   */
  private int rightOf(int i) {
    return ((2 * i + 2) <= (heapQueue.size()-1)) ? (2 * i + 2) : (-1);
  }

  /**
   *
   * returns the left children of i
   * @param i
   * @return the left children of the index in input
   */
  private int leftOf(int i){
    return ((2 * i + 1) <= (heapQueue.size()-1)) ? (2 * i + 1) : (-1);
  }

  /**
   *
   * swaps element at position p1 with element at position p2
   * @param p1 position 1
   * @param p2 position 2
   */
  private void swap(int p1, int p2){
      hashUpdateIndexes(p1, p2);
      QueueNode<T,E> temp = heapQueue.get(p1);
      heapQueue.set(p1, heapQueue.get(p2)); 
      heapQueue.set(p2, temp); 
  }
}

