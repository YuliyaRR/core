package org.example.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuickSortTest {
    @Test
    public void quickSort(){
        int[] arr = {15, 78, 10, 33, 44, 52, 21};
        int[] arrExpected = {10, 15, 21, 33, 44, 52, 78};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(arrExpected, arr);
    }

    @Test
    public void quickSort2(){
        int[] arr = {15, 15, 10, 2, 44, 52, 3};
        int[] arrExpected = {2, 3, 10, 15, 15, 44, 52};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(arrExpected, arr);
    }

    @Test
    public void quickSort3(){
        int[] arr = {15, 78, 10, 33, 44, 52, 3};
        int[] arrExpected = {3, 10, 15, 33, 44, 52, 78};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(arrExpected, arr);
    }

    @Test
    public void quickSort4(){
        int[] arr = {15, 16, 100, 200, 404, 520, 3000};
        int[] arrExpected = {15, 16, 100, 200, 404, 520, 3000};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(arrExpected, arr);
    }

    @Test
    public void quickSort5(){
        int[] arr = {15, 6, 1, 0, -4, -5, -30};
        int[] arrExpected = {-30, -5, -4, 0, 1, 6, 15};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(arrExpected, arr);
    }

    @Test
    public void quickSort6(){
        int[] arr = {15, 78, 10, 44, 52, 3};
        int[] arrExpected = {3, 10, 15, 44, 52, 78};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(arrExpected, arr);
    }

    @Test
    public void quickSort7(){
        int[] arr = {0, 0, 0, 7, 0, 0};
        int[] arrExpected = {0, 0, 0, 0, 0, 7};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(arrExpected, arr);
    }

    @Test
    public void quickSort8(){
        int[] arr = {0, 0, 0, 0, 0, -1};
        int[] arrExpected = {-1, 0, 0, 0, 0, 0};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(arrExpected, arr);
    }

    @Test
    public void quickSort9(){
        int[] arr = {3, -1};
        int[] arrExpected = {-1, 3};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(arrExpected, arr);
    }

    @Test
    public void quickSort10(){
        int[] arr = {0};
        int[] arrExpected = {0};
        QuickSort.quickSort(arr);
        Assertions.assertArrayEquals(arrExpected, arr);
    }
}
