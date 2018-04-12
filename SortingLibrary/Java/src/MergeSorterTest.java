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

/**
 *
 * @author Lorenzo Marietta
 */
public class MergeSorterTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test of sort method, of class MergeSorter, with ArrayList==null.
     */
    @Test
    public void testNullSort() throws Exception {
        Comparator <Long> comparator = initComparator();
        MergeSorter<Long> instance = new MergeSorter<>(comparator, null);
        thrown.expect(SortException.class);
        thrown.expectMessage("ArrayList cannot be null");
        instance.increasingSort();
    }

    @Test(timeout = 600000)
    public void testIncreasingSort() throws Exception {
        MergeSorter<Long> instance;
        ArrayList<Long> integers = initIntegersArrayList();
        Comparator <Long> comparator = initComparator();
        instance = new MergeSorter<>(comparator, integers);
        instance.increasingSort();
        assert (isSorted(true, integers));
    }

    /**
     * Test of sort method of class MergeSorter.
     */
    @Test(timeout = 600000)
    public void testSort() throws SortException {
        MergeSorter<Long> instance;
        ArrayList<Long> integers = initIntegersArrayList();
        Comparator <Long> comparator = initComparator();
        instance = new MergeSorter<>(comparator, integers);
        instance.sort();
        assert (isSorted(integers));
    }

    private boolean isSorted( ArrayList array) {
        int size = array.size() - 1;
        Comparator c = initComparator();
        for (int i = 0; i < size; i++) {
            if (c.compare(array.get(i), array.get(i + 1)) > 0) {
            return false;
        }
        return true;
    }

    private Comparator initComparator() {
        Comparator<Long> c = new Comparator<Long>() {
            @Override
            public int compare(Long a, Long b) {
                if (a > b) {
                    return 1;
                } else if (a < b) {
                    return -1;
                }else{
                    return 0;
                }
            }
        };
        return c;
    }

    private ArrayList initIntegersArrayList() {
        BufferedReader b;
        ArrayList<Long> a = null;
        try {
            b = new BufferedReader(new FileReader("../res/integers.csv"));
            String s;
            a = new ArrayList<>();
            while ((s = b.readLine()) != null) {
                a.add(Long.parseLong(s));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MergeSorterTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MergeSorterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
}
