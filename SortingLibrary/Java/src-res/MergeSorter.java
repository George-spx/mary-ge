/*
 * Copyright (C) 2018 Lorenzo Marietta
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Object MergeSorter can be associated to an ArrayList, 
 * and used to order it when the sort() metod is called.
 * @author Lorenzo Marietta
 * @param <T> 
 */
public class MergeSorter<T> {

    private ArrayList<T> array;
    private ArrayList<T> tempArray;
    private Comparator<? super T> comparator = null;
    private int length;

    /**
     * 
     * @param comparator
     * @param array 
     */
    public MergeSorter(Comparator<? super T> comparator, ArrayList<T> array) {
        this.comparator = comparator;
        this.array = array;
    }

    /**
     * Sort all the elements in the ArrayList.
     * @throws mergesort.SortException
     */
    public void increasingSort() throws SortException{
        if(array==null)throw new SortException("ArrayList cannot be null");
        this.length = array.size();
        tempArray = new ArrayList<>();
        for (int i = 1; i< length; i *= 2) {
            for (int j = i; j < length; j += 2 * i) {
                increasingMerge(j - i, j, Math.min(j + i, length));
            }
        }
        
    }

    /**
     * Sort all the element in the ArrayList.
     * @throws mergesort.SortException
     */
    public void decreasingSort() throws SortException {
        if(array==null)throw new SortException("ArrayList cannot be null");
        this.length = array.size();
        tempArray = new ArrayList<>();
        for (int i = 1; i< length; i *= 2) {
            for (int j = i; j < length; j += 2 * i) {
                decreasingMerge(j - i, j, Math.min(j + i, length));
            }
        }
        
    }

    /**
     * 
     * @param start index of beginning left part to merge.
     * @param middle left part end index, and start right side to merge.
     * @param end right part end index to merge.
     */
    private void increasingMerge(int start, int middle, int end) {
        int left = 0;
        int right = 0;
        tempArray.clear();
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
     * 
     * @param start index of beginning left part to merge.
     * @param middle left part end index, and start right side to merge.
     * @param end right part end index to merge.
     */
    private void decreasingMerge(int start, int middle, int end) {
        int left = 0;
        int right = 0;
        tempArray.clear();
        while (left < middle - start && right < end - middle) {
            if (comparator.compare(array.get(start + left), array.get(middle + right)) > 0) {
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

}
