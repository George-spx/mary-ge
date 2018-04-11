import java.util.Comparator;
import java.util.ArrayList;

/**
 *@author George
 *
 */

public class InsertionSort <T>{
  ArrayList<T> array = null;
  Comparator <? super T> comparator;
  boolean crescentOrder;

 /**
  *
  * initializes the comparator and the array
  * @param comparator
  */
    public InsertionSort(Comparator <? super T> comparator, boolean crescentOrder){
      this.comparator = comparator;
      this.array = new ArrayList<>();
      this.crescentOrder = crescentOrder;
    }

 /**
  *
  * @param element element to be added to array
  */
    public void add(T element)throws SortException{
      if(element == null){
	throw new SortException("element cannot be null");	
      }else if((array.size()) == 0){
	this.array.add(element);
      }else{
	this.array.add(element);
	sort();		
      }
    }
	
 /**
  * sorts the array, after the elements is added 
  */
    public void sort(){
      if(array != null){
        if(crescentOrder){
          crescentOrderSort();   
        }
        else{
          decrescentOrderSort();
        }
      }
    }

  /**
   * places the last element added in the right position
   *
   */
    public void crescentOrderSort(){
      boolean ordered = false;
      for(int j = (array.size()-1); j >= 1 && !ordered; j--){
        if((comparator.compare(array.get(j-1), array.get(j))) >= 0){
          swap(j-1, j);
        }else{
          ordered = true;
        }
      }  
    }

  /**
   * places the last element added in the right position
   *
   */
    public void decrescentOrderSort(){
      boolean ordered = false;
      for(int j = (array.size()-1); j >= 1 && !ordered; j--){
        if((comparator.compare(array.get(j-1), array.get(j))) <= 0){
          swap(j-1, j);
        }else{
          ordered = true;
        }
      }  
    }


 /**
  * 
  * swaps elements at position p, n in the array.
  * @param p1 position 1
  * @param p1 position 2
  *
  */
    void swap(int p1, int p2) {
      T tmp = array.get(p1);
      array.set(p1, array.get(p2));
      array.set(p2, tmp);
    }
}
