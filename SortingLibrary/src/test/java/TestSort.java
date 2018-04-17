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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TestSort{

  @Test
  public void testInsertionSortEmptyArrayList() throws SortException{
    Comparator <Integer> comparator = initializeIntegerComparator();
    ArrayList <Integer> expectedArray = new ArrayList<Integer>();
    
    ArrayList<Integer> toSort = new ArrayList<Integer>();
    Sort.insertionSort(toSort, comparator);

    assertEquals(expectedArray, toSort);
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testInsertionSortNullArrayList() throws SortException{
    Comparator <Integer> comparator = initializeIntegerComparator();

    thrown.expect(SortException.class);
    thrown.expectMessage("ArrayList cannot be null");
    Sort.<Integer>insertionSort(null, comparator);
  }


  @Test
  public void testInsertionSortSameElement()throws SortException{
    Comparator <Integer> comparator = initializeIntegerComparator();
    ArrayList <Integer> expectedArray = new ArrayList<Integer>();
    ArrayList <Integer> testArray = new ArrayList<Integer>();
    expectedArray.add(3);
    expectedArray.add(3);
    expectedArray.add(3);
    expectedArray.add(3);

    testArray.add(3);
    testArray.add(3);
    testArray.add(3);
    testArray.add(3);
    Sort.<Integer>insertionSort(testArray, comparator);
   
    assertEquals(testArray, expectedArray); 
  }

  @Test
  public void testMergeSortSameElement()throws SortException{
    Comparator <Integer> comparator = initializeIntegerComparator();
    ArrayList <Integer> expectedArray = new ArrayList<Integer>();
    ArrayList <Integer> testArray = new ArrayList<Integer>();
    expectedArray.add(3);
    expectedArray.add(3);
    expectedArray.add(3);
    expectedArray.add(3);

    testArray.add(3);
    testArray.add(3);
    testArray.add(3);
    testArray.add(3);
    Sort.<Integer>mergeSort(testArray, comparator);
   
    assertEquals(testArray, expectedArray); 
  }

  @Test
  public void testInsertionSort()throws SortException{
    Comparator <Integer> comparator = initializeIntegerComparator();
    ArrayList <Integer> expectedArray = new ArrayList<Integer>();
    ArrayList <Integer> testArray = new ArrayList<Integer>();
    expectedArray.add(2);
    expectedArray.add(2);
    expectedArray.add(52);
    expectedArray.add(256);

    testArray.add(52);
    testArray.add(2);
    testArray.add(256);
    testArray.add(2);
    Sort.<Integer>insertionSort(testArray, comparator);
   
    assertEquals(testArray, expectedArray); 
  }

  @Test
  public void testMergeSort()throws SortException{
    Comparator <Integer> comparator = initializeIntegerComparator();
    ArrayList <Integer> expectedArray = new ArrayList<Integer>();
    ArrayList <Integer> testArray = new ArrayList<Integer>();
    expectedArray.add(2);
    expectedArray.add(2);
    expectedArray.add(52);
    expectedArray.add(256);

    testArray.add(52);
    testArray.add(2);
    testArray.add(256);
    testArray.add(2);
    Sort.<Integer>mergeSort(testArray, comparator);
   
    assertEquals(testArray, expectedArray); 
  }


  public Comparator<Integer> initializeIntegerComparator(){
    Comparator<Integer> comparator = new Comparator<Integer>(){
      @Override
      public int compare(Integer el1, Integer el2){
        return el1 - el2;
      }
    };  
    return comparator;
  }

  @Test
  public void testMergeSortEmptyArrayList() throws SortException{
    Comparator <Integer> comparator = initializeIntegerComparator();
    ArrayList <Integer> expectedArray = new ArrayList<Integer>();
    
    ArrayList<Integer> toSort = new ArrayList<Integer>();
    Sort.mergeSort(toSort, comparator);

    assertEquals(expectedArray, toSort);
  }

  @Test
  public void testMergeSortNullArrayList() throws SortException{
    Comparator <Integer> comparator = initializeIntegerComparator();

    thrown.expect(SortException.class);
    thrown.expectMessage("ArrayList cannot be null");
    Sort.<Integer>mergeSort(null, comparator);
  }

}
