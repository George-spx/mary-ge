<<<<<<< HEAD
=======
  
>>>>>>> eae6c5748ab01a5d51eb8e4477aee18c3ab5ed1b
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
package mergesort;

import.java.util.ArrayList;

/**
 *
 * @author Lorenzo Marietta, George
 */
import java.util.ArrayList;
public class Sum {
  /**
   * method that checks wheter the element N is contained in array A
   * as a sum of two elements
   */
  public boolean void sumFinder(long N, ArrayLyst<Long> A)(
    for(int i=0; i<A.getSize(); i++){
		return binarySearch (N - a.get(i), A) != -1;
	}
  
  }
  
  private static int binarySearch(long difference, ArrayList<Long> A){
    return recBinarySearch(difference, 0, A.size()-1, A); 
  } 

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
<<<<<<< HEAD
=======

  
}
>>>>>>> eae6c5748ab01a5d51eb8e4477aee18c3ab5ed1b
