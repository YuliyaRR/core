package org.example.algorithms;

public class QuickSort {

    private QuickSort(){}

    /**
     * Метод сортирует переданный массив по возрастанию используя алгоритм быстрой сортировки.
     * Скорость работы алгоритма - O(n log n).
     * @param arr - массив элементов типа int
     */
    public static void quickSort(int[] arr){
        quickSortLastElementIsPivot(arr, 0, arr.length - 1);
        //quickSortFirstElementIsPivot(arr, 0, arr.length - 1);
        //quickSortMiddleElementIsPivot(arr, 0, arr.length - 1);

    }

    /**
     * Реализация алгоритма быстрой сортировки, где поворотный элемент является последним элементом в переданом интервале.
     * @param arr - массив элементов типа int,
     * @param start - индекс начала интервала для сортировки
     * @param finish - индекс конца интервала для сортировки
     */
    private static void quickSortLastElementIsPivot(int[] arr, int start, int finish) {
        if(start >= finish) {
            return;
        }

        int i = start - 1;
        int pivot = arr[finish];

        for (int j = start; j < finish ; j++) {
            if(arr[j] < pivot) {
                i++;
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
            }
        }

        int tmp = arr[i+1];
        arr[i+1] = pivot;
        arr[finish] = tmp;

        quickSortLastElementIsPivot(arr, start, i);
        quickSortLastElementIsPivot(arr, i + 2, finish);
    }

    /**
     * Реализация алгоритма быстрой сортировки, где поворотный элемент является первым элементом в переданом интервале.
     * @param arr - массив элементов типа int,
     * @param start - индекс начала интервала для сортировки
     * @param finish - индекс конца интервала для сортировки
     */
    private static void quickSortFirstElementIsPivot(int[] arr, int start, int finish) {
        if(start >= finish) {
            return;
        }

        int i = finish;
        int pivot = arr[start];

        for (int j = finish; j >= start; j--) {
            if(pivot < arr[j]) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i--;
            }
        }

        int tmp = arr[i];
        arr[i] = pivot;
        arr[start] = tmp;

        quickSortFirstElementIsPivot(arr, start, i - 1);
        quickSortFirstElementIsPivot(arr, i + 1, finish);
    }

    /**
     * Реализация алгоритма быстрой сортировки, где поворотный элемент является средним элементом в переданом интервале.
     * @param arr - массив элементов типа int,
     * @param start - индекс начала интервала для сортировки
     * @param finish - индекс конца интервала для сортировки
     */
    private static void quickSortMiddleElementIsPivot(int[] arr, int start, int finish) {
        if(start >= finish) {
            return;
        }

        int middleIndex = start + (finish - start) / 2;
        int pivot = arr[middleIndex];

        int i = start;
        int j = finish;

        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if(i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }

        if(start < j) {
            quickSortMiddleElementIsPivot(arr, start, j);
        }

        if(i < finish) {
            quickSortMiddleElementIsPivot(arr, i, finish);
        }

    }
}
