import java.util.Comparator;
import java.util.ArrayList;

/**
 *@author George
 *
 */

public class Sort <T>{

    /**
     * Sort all the elements in the ArrayList.
     * @throws SortException
     */
    public static <T> void mergeSort(ArrayList<T> array, Comparator<T> comparator) throws SortException{
      if(array == null){
        throw new SortException("ArrayList cannot be null");
      }
      int length = array.size();
      for (int i = 1; i< length; i *= 2) {
        for (int j = i; j < length; j += 2 * i) {
          merge(array, comparator, j - i, j, Math.min(j + i, length));
        }
      }
    }

    /**
     * 
     * @param start index of beginning left part to merge.
     * @param middle left part end index, and start right side to merge.
     * @param end right part end index to merge.
     */
    private static <T> void merge(ArrayList<T> array, Comparator<T> comparator, int start, int middle, int end) {
        int left = 0;
        int right = 0;
        ArrayList<T> tempArray = new ArrayList<T>();
        while (left < middle - start && right < end - middle) {
            if (comparator.compare(array.get(start + left), array.get(middle + right)) < 0) {
                tempArray.add(array.get(start + left++));
            } else {
                tempArray.add(array.get(middle + right++));
            }
        }
        while (left < middle - start) {
            tempArray.add(array.get(start + left++));
        }
        while (right < end - middle) {
            tempArray.add(array.get(middle + right++));
        }
        for (int n = 0; n < end - start; n++) {
            array.set(n + start, tempArray.get(n));
        }
    }

    /**
     * sorts the array using the insertionSort algorithm
     * @param array the array to be ordered
     * @param comparator the rules used to compare
     */
    public static <T> void insertionSort(ArrayList<T> array, Comparator<T> comparator) throws SortException{
      if(array == null){
        throw new SortException("ArrayList cannot be null");
      }
        T value = null;
        for(int i = 1; i < array.size(); i++){
          value = array.get(i);
          for(int j = i -1; j >= 0; j--){
            if((comparator.compare(array.get(j), value)) > 0){
              swap(array, j+1, j);
            }	
            else{
              break;
            }
          }	
        }
    }

    /**
     * the method swaps the elements between position1 and position2
     * @param p1 position1
     * @param p2 position2
     */
    private static <T> void swap(ArrayList<T> array, int p1, int p2) {
      T tmp = array.get(p1);
      array.set(p1, array.get(p2));
      array.set(p2, tmp);
    }
}
