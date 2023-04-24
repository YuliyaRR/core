package org.example.algorithms;

public class BinarySearch {
    private BinarySearch() {
    }

    /**
     * Метод осуществляет поиск элемента в отсортированном массиве используя алгоритм бинарного поиска.
     * Скорость работы алгоритма - O(log n).
     * @param arr - отсортированный массив элементов типа int
     * @param element - элемент, который нужно найти
     * @return - индекс элемента в массиве или -1, если такой элемент не найден
     * @throws IllegalArgumentException - если переданный массив не отсортирован
     */
     public static int binarySearch(int[] arr, int element) {
        if (!isSort(arr)) {
            throw new IllegalArgumentException("Переданный массив должен быть отсортирован");
        }
        int startIndex = 0;
        int finishIndex = arr.length - 1;

        while (startIndex <= finishIndex) {
            int middleIndex = (startIndex + finishIndex) / 2;
            int tmp = arr[middleIndex];
            if (tmp == element) {
                return middleIndex;
            } else if (tmp < element) {
                startIndex = middleIndex + 1;
            } else { // tmp > element
                finishIndex = middleIndex - 1;
            }
        }
        return -1;
    }

    /**
     * Метод проверяет отсортирован ли переданный массив по возрастанию
     * @param arr - массив элементов типа int
     * @return - true - если массив отсортирован, false - если нет.
     */
    private static boolean isSort(int[] arr) {
        return arr[0] < arr[1];
    }
}
