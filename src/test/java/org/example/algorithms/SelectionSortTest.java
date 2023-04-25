package org.example.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SelectionSortTest {
    @Test
    public void sort() {
        int[] arr = {5, 7, 0, -3, 4, 2, 1};
        int[] expected = {-3, 0, 1, 2, 4, 5, 7};
        SelectionSort.sort(arr);
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    public void sort2() {
        int[] arr = {9, 7, 6, 5, 4, 2, 1};
        int[] expected = {1, 2, 4, 5, 6, 7, 9};
        SelectionSort.sort(arr);
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    public void sort3() {
        int[] arr = {9, 7, 5, 5, 4, 2, 5};
        int[] expected = {2, 4, 5, 5, 5, 7, 9};
        SelectionSort.sort(arr);
        Assertions.assertArrayEquals(expected, arr);
    }
}
