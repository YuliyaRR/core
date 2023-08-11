package org.example.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
//null isn't supported
public class MyArrayList<E> implements IList<E>{
    private final int DEFAULT_SIZE = 10;
    private Object[] innerArray;
    private int currentSize;//расчетный показатель
    private int countElement;//фактическое кол-во элементов(null не считаются)

    public MyArrayList() {
        this.innerArray = new Object[DEFAULT_SIZE];
        this.currentSize = DEFAULT_SIZE;
    }

    @Override
    public int size() {
        return countElement;
    }

    @Override
    public boolean add(E element) {
        if(element == null) {
            throw new NullPointerException();
        }
        if (!checkSize()){
            innerArray = increaseInnerArray();
        }
        innerArray[countElement] = element;
        countElement++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        checkIndex(index);

        if(!checkSize()){
            innerArray = increaseInnerArray();
        }

        for (int i = countElement - 1; i >= index; i--) {
            innerArray[i+1] = innerArray[i];
        }

        innerArray[index] = element;
        countElement++;
        return true;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) innerArray[index];
    }

    @Override
    public boolean remove(int index) {
        checkIndex(index);
        for (int i = index; i < countElement - 1; i++) {
            innerArray[i] = innerArray[i+1];
        }
        innerArray[countElement-1] = null;
        countElement--;
        return true;
    }

    @Override
    public boolean remove(E element) {
        if(element == null) {
            throw new NullPointerException();
        }

        boolean isFound = false;

        for (int i = 0; i < countElement - 1; i++) {
            if(Objects.equals(innerArray[i], element) && !isFound){
                isFound = true;
            }

            if(isFound){
                innerArray[i] = innerArray[i+1];
            }
        }

        innerArray[countElement-1] = null;
        countElement--;
        return true;
    }
    @Override
    public void clear() {
        innerArray = new Object[DEFAULT_SIZE];
        countElement = 0;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        trimToSize();
        mergeSort((E[])innerArray, comparator);
    }

    @Override
    public boolean contains(E element) {
        if(element == null) {
            throw new NullPointerException();
        }
         return Arrays.stream(innerArray).anyMatch(element::equals);
    }

    private void trimToSize() {
        innerArray = Arrays.copyOf(innerArray, countElement);
        currentSize = innerArray.length;
    }

    private void mergeSort(E[] arr, Comparator<? super E> comparator) {
        if(arr.length < 2) {
            return;
        }

        E[] leftArr = Arrays.copyOfRange(arr, 0, arr.length/2);
        E[] rightArr = Arrays.copyOfRange(arr, arr.length/2, arr.length);

        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);

        int indexLeftArr = 0;
        int indexRightArr = 0;

        for (int i = 0; i < arr.length; i++) {
            if(indexLeftArr == leftArr.length) {
                arr[i] = rightArr[indexRightArr];
                indexRightArr++;
            } else if(indexRightArr == rightArr.length) {
                arr[i] = leftArr[indexLeftArr];
                indexLeftArr++;
            } else if (comparator.compare(leftArr[indexLeftArr], rightArr[indexRightArr]) <= 0) {
                arr[i] = leftArr[indexLeftArr];
                indexLeftArr++;
            } else { /*(comparator.compare(leftArr[indexLeftArr], rightArr[indexRightArr]) >= 0)*/
                arr[i] = rightArr[indexRightArr];
                indexRightArr++;
            }
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int curElement = 0;

            @Override
            public boolean hasNext() {
                return curElement < innerArray.length;
            }

            @Override
            public E next() {
                int nextIndex = curElement;
                if (nextIndex >= innerArray.length) {
                    throw new IndexOutOfBoundsException();
                } else {
                    Object[]copyArr = Arrays.copyOf(innerArray, innerArray.length);
                    E next = (E) copyArr[nextIndex];
                    curElement++;
                    return next;
                }
            }
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof MyArrayList)) {
            return false;
        }

        MyArrayList<E> list = (MyArrayList<E>) obj;

        if(this.size() != list.size()) {
            return false;
        }

        for (int i = 0; i < innerArray.length; i++) {
            if(!Objects.equals(innerArray[i],list.innerArray[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(countElement);
        result = 31 * result + Arrays.hashCode(innerArray);
        return result;
    }
    /*
    * Метод выводит содержимое внутреннего массива в [] и без ячеек, в которых хранится null.
    * @return - содержимое внутреннего массива в [].
    */
    @Override
    public String toString() {
        if(countElement == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();

        builder.append("[");

        for (int i = 0; i < this.size(); i++) {
            if(i != this.size() - 1) {
                builder.append(innerArray[i]).append(", ");
            } else {
                builder.append(innerArray[i]).append("]");
            }
        }

        return builder.toString();
    }

    /*
    * Метод проверяет нужно ли увеличивать внутренний массив
    * return - true - внутренний массив подходящего размера,
    *          false - нужно увеличивать
    * */
    private boolean checkSize(){
        return countElement < currentSize;
    }

    private void checkIndex(int index) {
        if(index >= currentSize || index < 0){
            throw new IndexOutOfBoundsException();
        }
    }

    private Object[] increaseInnerArray() {
        this.currentSize = (currentSize * 3) / 2 + 1;
        return Arrays.copyOf(innerArray, currentSize);
    }


}
