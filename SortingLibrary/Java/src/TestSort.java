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

  @Test(timeout = 600000) 
  public void testIntegerUseCaseIncreasing() throws SortException{
   Comparator<Long> comparator = initializeLongComparator(); 
   BufferedReader readFile = initializeBufferedReader("../res/sums.txt");
   ArrayList<Long> integers = new ArrayList<Long>();
   populateArray(integers, readFile);
   Sort.<Long>insertionSort(integers, comparator);

   assertTrue(isArrayOrdered(integers));
  }

  public boolean isArrayOrdered(ArrayList<Long> array){
    for(int i = 0; i < array.size()-1; i++){
      if(array.get(i) > array.get(i+1)){
        return false;
      }
    }
    return true;
  }

  public void populateArray(ArrayList<Long> integers, BufferedReader readFile){
    String stringRead;
    try{
      while((stringRead = readFile.readLine()) != null){
      integers.add(Long.parseLong(stringRead));
      } 
    }catch(IOException e){
      System.out.println(e);
      fail();
    }
  }

  public BufferedReader initializeBufferedReader(String fileName){
    try{
      BufferedReader readFile = new BufferedReader(new FileReader(fileName));
      return readFile;
    }catch(FileNotFoundException e){
      System.out.println(e);
      fail();
    }
    return null;
  }

  public Comparator<Long> initializeLongComparator(){
    Comparator <Long> comparator = new Comparator<Long>(){
      @Override
      public int compare(Long e1, Long e2){
        long res = e1-e2;
        if(res < 0){
          return -1;
        }else if(res > 0){
          return 1;
        }else{
          return 0;
        }
      }
    };
    return comparator;
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

    /**
     * Test of sort method of class MergeSorter.
     */
    @Test(timeout = 600000)
    public void testMergeSort() throws SortException {
        BufferedReader readFile = initializeBufferedReader("../res/integers.csv");
        ArrayList<Long> integers = new ArrayList<Long> ();
        populateArray(integers, readFile);
        Comparator <Long> comparator = initializeLongComparator();

        Sort.<Long>mergeSort(integers, comparator);

        assert (isSorted(integers));
    }

    private boolean isSorted(ArrayList<Long> array) {
        int size = array.size() - 1;
        Comparator<Long> c = initializeLongComparator();
        for (int i = 0; i < size; i++) {
            if (c.compare(array.get(i), array.get(i + 1)) > 0) {
              return false;
            }
       }
      return true;
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
