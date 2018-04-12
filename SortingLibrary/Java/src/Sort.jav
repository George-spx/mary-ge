import java.util.Comparator;
import java.util.ArrayList;

/**
 *@author George
 *
 */

public class Sort <T>{


  
    public static void mergeSort(ArrayList<T> array, Comparator<T> comparator){
      if(array!=null)throw new SortException("ArrayList cannot be null");
        this.length = array.size();
        tempArray = new ArrayList<>();
        for (int i = 1; i< length; i *= 2) {
          for (int j = i; j < length; j += 2 * i) {
            merge(j - i, j, Math.min(j + i, length));
          }
        }
      } 

    /**
     * sorts the array using the insertionSort algorithm
     * @param array the array to be ordered
     * @param comparator the rules used to compare
     */
    public static void insertionSort(ArrayList<T> array, Comparator<T> comparator){
      if(array != null){
        T value = null;
        for(int i = 1; i < array.size(); i++){
          value = array.get(i);
          for(int j = i -1; j >= 0; j--){
            if((comparator.compare(array.get(j), value)) > 0){
              swap(j+1, j);
            }	
            else{
              break;
            }
          }	
        }
      }
    }

    /**
     * the method swaps the elements between position1 and position2
     * @param p1 position1
     * @param p2 position2
     */
    private void swap(int p1, int p2) {
      T tmp = array.get(p1);
      array.set(p1, array.get(p2));
      array.set(p2, tmp);
    }
}
