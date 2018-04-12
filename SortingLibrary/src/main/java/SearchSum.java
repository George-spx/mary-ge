/*
 * Copyright (C) 2018 Lorenzo Marietta, George
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

/**
 *
 * @author Lorenzo Marietta, George
 */

import java.util.ArrayList;

public class SearchSum{

  /**
   * method that checks wheter the element N is contained in array A
   * as a sum of two elements
   * @param N the number that represents the sum
   * @param A the ordered array containing the elements
   * @return true if and only if A has two elements that summed give N, false otherwise
   */
  public static boolean sumFinder(long N, ArrayList<Long> A){
    boolean result = false;
    for(int i=0; i < A.size() && !result; i++){
      result = N > A.get(i) ? (-1 != (binarySearch(N - A.get(i), A))) : false;
    }
    return result;
  }
 
 /**
  * instantiates the indexes needed to apply the binary-search
  * @param difference the element to be found
  * @param A the array containing the elements 
  * @return if A contains the element: the index of the element, -1 otherwise
  */
  private static int binarySearch(long difference, ArrayList<Long> A){
    return recBinarySearch(difference, 0, A.size()-1, A); 
  } 

  /**
   * applies the recursive binary search algorithm
   * @param difference the element to be found
   * @param l left index of the array/subarray
   * @param r right index of the array/subarray
   * @param A the array containing the elements
   * @return if A contains the element: the index of the element, -1 otherwise
   */
  private static int recBinarySearch(long difference, int l, int r, ArrayList<Long> A){
    int m = (l + r) / 2;
    if(l > r) {
      return -1;
    } 
    if(difference == A.get(m)) {
      return m;
    }else if(difference < A.get(m)) {
      return recBinarySearch(difference, l, m - 1, A);
    }else {
      return recBinarySearch(difference, m + 1, r, A);
    }
  }

}
