package org.example.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinarySearchTest {

    @Test
    public void search() {
        int [] arr = {1, 7, 15, 19, 25, 115, 2478};
        Assertions.assertEquals(2, BinarySearch.binarySearch(arr, 15));
    }

    @Test
    public void search2() {
        int [] arr = {1, 7, 15, 19, 25, 115, 2478};
        Assertions.assertEquals(5, BinarySearch.binarySearch(arr, 115));
    }

    @Test
    public void search3() {
        int [] arr = {1, 7, 15, 19, 25, 115, 2478};
        Assertions.assertEquals(-1, BinarySearch.binarySearch(arr, 100));
    }

    @Test
    public void search4() {
        int [] arr = {15, 0, 815, 3, 11, 9, 1};
        Assertions.assertThrows(IllegalArgumentException.class, () -> BinarySearch.binarySearch(arr, 3));
    }
}
