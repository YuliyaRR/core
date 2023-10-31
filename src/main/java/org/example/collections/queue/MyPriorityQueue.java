package org.example.collections.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class MyPriorityQueue<E> implements MyQueue<E> {
    private final static int DEFAULT_INIT_CAPACITY = 8;
    private E[] queue;
    private int amountElement;
    private Comparator<? super E> comparator;
    private boolean isComparatorFromComparable;

    public MyPriorityQueue() {
        this.queue = (E[]) new Object[DEFAULT_INIT_CAPACITY];
        initComparator();
        this.isComparatorFromComparable = true;
    }

    public MyPriorityQueue(Comparator<? super E> comparator) {
        this.queue = (E[]) new Object[DEFAULT_INIT_CAPACITY];
        this.comparator = comparator;
    }

    /**
     * Метод добавляет элемент в очередь
     * @param element элемент для добавления
     * @return true - если элемент был добавлен
     */
    @Override
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException("Null isn't supported in this collection");
        }

        if (amountElement >= queue.length) {
            increaseInnerArray();
        }

        int indIns = amountElement;

        siftUp(indIns, element, comparator);

        amountElement++;

        return true;
    }

    /**
     * Метод извлекает, но не удаляет первый элемент в очереди
     * @return первый элемент в очереди или null, если очередь пуста
     */
    @Override
    public E peek() {
        return queue[0];
    }

    /**
     * Метод извлекает и удаляет первый элемент в очереди
     * @return первый элемент в очереди или null, если очередь пуста
     */
    @Override
    public E poll() {
        E firstElement = queue[0];
        this.amountElement--;

        if (firstElement != null && this.amountElement != 0) {

            queue[0] = queue[amountElement];
            queue[amountElement] = null;

            siftDown(0, this.queue[0], comparator);
        }

        return firstElement;
    }

    @Override
    public int size() {
        return amountElement;
    }

    private void initComparator() {
        this.comparator = (E o1, E o2) -> {
            Comparable<? super E> obj = (Comparable<? super E>) o1;
            return obj.compareTo(o2);
        };
    }

    private void increaseInnerArray() {
        int newLength = queue.length + 1;
        E[] newArr = (E[]) new Object[newLength];
        System.arraycopy(queue, 0, newArr, 0, amountElement);
        queue = newArr;
    }

    private void siftUp(int indIns, E value, Comparator<? super E> cmp) {
        if (indIns == 0) {
            if (isComparatorFromComparable) {
                var val = (Comparable<? super E>) value;
            }
            queue[indIns] = value;
        }

        for (int i = indIns; i > 0; ) {
            int indParent = defineParentIndexForSiftUp(i);

            // compare parent and child elements
            if (cmp.compare(value, queue[indParent]) > 0) {
                queue[i] = value;
                break;
            } else {
                E tmp = queue[indParent];
                queue[indParent] = value;
                queue[i] = tmp;
                i = indParent;
            }
        }

    }

    private void siftDown(int index, E value, Comparator<? super E> cmp) {
        for (int i = index; i < this.queue.length; ) {
            int indexMinElement = compareChildrenElements(i, cmp);

            if (indexMinElement == -1) {
                break;
            }

            if (cmp.compare(value, queue[indexMinElement]) <= 0) {
                break;
            } else {
                queue[i] = queue[indexMinElement];
                queue[indexMinElement] = value;
                i = indexMinElement;
            }
        }
    }

    private int defineParentIndexForSiftUp(int index) {
        int indParent;
        if (index % 2 == 1) {
            indParent = index / 2;
        } else {
            indParent = index / 2 - 1;
        }
        return indParent;
    }

    private int compareChildrenElements(int indexParentElement, Comparator<? super E> cmp) {
        int indexLeft = indexParentElement * 2 + 1;
        int indexRight = indexParentElement * 2 + 2;

        if (indexLeft >= amountElement && indexRight >= amountElement) {
            return -1;
        } else if ((indexLeft < amountElement && indexRight >= amountElement)) {
            return indexLeft;
        } else {
            return cmp.compare(queue[indexLeft], queue[indexRight]) <= 0 ? indexLeft : indexRight;
        }
    }

    @Override
    public String toString() {
        if (amountElement == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();

        builder.append("[");

        for (int i = 0; i < amountElement; i++) {
            if (i != amountElement - 1) {
                builder.append(queue[i]).append(", ");
            } else {
                builder.append(queue[i]).append("]");
            }
        }

        return builder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int curElement = 0;

            @Override
            public boolean hasNext() {
                return curElement < queue.length;
            }

            @Override
            public E next() {
                int nextIndex = curElement;
                if (nextIndex >= queue.length) {
                    throw new IndexOutOfBoundsException();
                } else {
                    E[] copyArr = Arrays.copyOf(queue, queue.length);
                    E next = copyArr[nextIndex];
                    curElement++;
                    return next;
                }
            }
        };
    }
}
