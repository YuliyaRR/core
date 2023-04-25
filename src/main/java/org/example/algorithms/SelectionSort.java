package org.example.algorithms;

public class SelectionSort {

    private SelectionSort() {
    }

    /**
     * Метод сортирует переданный массив по возрастанию используя алгоритм сортировки выбором.
     * Скорость работы алгоритма - O(n^2).
     * @param arr - массив элементов типа int
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minElement = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] < minElement) {
                    minElement = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = minElement;
        }
    }
}
