package org.example.collections.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class MyPriorityQueue<E> implements MyQueue<E> {
    private final static int DEFAULT_INIT_CAPACITY = 8;
    private Object[] queue;
    private int amountElement;
    private Comparator<? super E> comparator;

    public MyPriorityQueue() {
        this.queue = new Object[DEFAULT_INIT_CAPACITY];
    }

    public MyPriorityQueue(Comparator<? super E> comparator) {
        this();
        this.comparator = comparator;
    }

    /**
     * Метод добавляет элемент в очередь
     * @param element элемент для добавления
     * @return true - если элемент был добавлен
     */
    @Override
    public boolean add(E element) {
        if(element == null) {
            throw new NullPointerException("Null isn't supported in this collection");
        }

        if (amountElement >= queue.length) {
            increaseInnerArray();
        }

        int indIns = amountElement;

        if(this.comparator == null) {
            siftUpComparable(indIns, element);
        } else {
            siftUpComparator(indIns, element, comparator);

        }

        amountElement++;

        return true;
    }

    /**
     * Метод извлекает, но не удаляет первый элемент в очереди
     * @return первый элемент в очереди или null, если очередь пуста
     */
    @Override
    public E peek() {
        return (E) queue[0];
    }

    /**
     * Метод извлекает и удаляет первый элемент в очереди
     * @return первый элемент в очереди или null, если очередь пуста
     */
    @Override
    public E poll() {
        E firstElement = (E) queue[0];
        this.amountElement--;

        if(firstElement != null && this.amountElement != 0) {

            queue[0] = queue[amountElement];
            queue[amountElement] = null;

            if(this.comparator == null) {
                siftDownComparable(0, (E) this.queue[0]);

            } else {
                siftDownComparator(0, (E) this.queue[0], comparator);
            }

        }

        return firstElement;
    }

    @Override
    public int size() {
        return amountElement;
    }

    private void increaseInnerArray() {
        int newLength = queue.length + 1;
        Object [] newArr = new Object[newLength];
        System.arraycopy(queue, 0, newArr, 0, amountElement);
        queue = newArr;
    }

    private void siftUpComparable(int indIns, E value) {
        Comparable<? super E> val = (Comparable<? super E>) value;

        if (indIns == 0) {
            queue[indIns] = val;
        }

        for (int i = indIns; i > 0; ) {
            int indParent = defineParentIndexForSiftUp(i);

            // compare parent and child elements
            if(val.compareTo((E) queue[indParent]) > 0) {
                queue[i] = val;
                break;
            } else {
                Object tmp = queue[indParent];
                queue[indParent] = val;
                queue[i] = tmp;
                i = indParent;
            }
        }
    }

    private void siftUpComparator(int indIns, E value, Comparator<? super E> cmp) {
        if (indIns == 0) {
            queue[indIns] = value;
        }

        for (int i = indIns; i > 0; ) {
            int indParent = defineParentIndexForSiftUp(i);

            // compare parent and child elements
            if(cmp.compare(value, (E) queue[indParent]) > 0) {
                queue[i] = value;
                break;
            } else {
                Object tmp = queue[indParent];
                queue[indParent] = value;
                queue[i] = tmp;
                i = indParent;
            }
        }

    }

    private void siftDownComparable(int index, E value) {
        Comparable<? super E> val = (Comparable<? super E>) value;

        for (int i = index; i < this.queue.length; ) {
            int indexMinElement = compareComparableChildrenElements(i);

            if(indexMinElement == -1) {
                break;
            }

            if(val.compareTo((E)queue[indexMinElement]) <= 0) {
                break;
            } else {
                queue[i] = queue[indexMinElement];
                queue[indexMinElement] = value;
                i = indexMinElement;
            }
        }
    }

    private void siftDownComparator(int index, E value, Comparator<? super E> cmp) {
        for (int i = index; i < this.queue.length; ) {
            int indexMinElement = compareChildrenElementsWithComparator(i, cmp);

            if(indexMinElement == -1) {
                break;
            }

            if(cmp.compare(value, (E)queue[indexMinElement]) <= 0) {
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

    private int compareComparableChildrenElements(int indexParentElement) {
        int indexLeft = indexParentElement * 2 + 1;
        int indexRight = indexParentElement * 2 + 2;

        if (indexLeft >= amountElement && indexRight >= amountElement) {
            return -1;
        } else if ((indexLeft < amountElement && indexRight >= amountElement)) {
            return indexLeft;
        } else {
            Comparable<? super E> leftChild = (Comparable<? super E>) queue[indexLeft];

            return leftChild.compareTo((E)queue[indexRight]) <= 0 ? indexLeft : indexRight;
        }
    }

    private int compareChildrenElementsWithComparator(int indexParentElement, Comparator<? super E> cmp) {
        int indexLeft = indexParentElement * 2 + 1;
        int indexRight = indexParentElement * 2 + 2;

        if (indexLeft >= amountElement && indexRight >= amountElement) {
            return -1;
        } else if ((indexLeft < amountElement && indexRight >= amountElement)) {
            return indexLeft;
        } else {
            return cmp.compare((E) queue[indexLeft], (E) queue[indexRight]) <= 0 ? indexLeft : indexRight;
        }
    }

    @Override
    public String toString() {
        if(amountElement == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();

        builder.append("[");

        for (int i = 0; i < amountElement; i++) {
            if(i != amountElement - 1) {
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
                    Object[]copyArr = Arrays.copyOf(queue, queue.length);
                    E next = (E) copyArr[nextIndex];
                    curElement++;
                    return next;
                }
            }
        };
    }
}
