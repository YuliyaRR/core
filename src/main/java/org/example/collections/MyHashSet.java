package org.example.collections;

import java.util.Iterator;

public class MyHashSet<E> implements ISet<E> {
    private static final float LOAD_FACTOR = 0.75f;
    private static final int INITIAL_CAPACITY = 16;
    private Entry<E>[] arr;
    private int amountOfOccupiedCells;
    private int amountOfElements;
    private int currentCapacity;

    public MyHashSet() {
        this.arr = new Entry[INITIAL_CAPACITY];
        this.currentCapacity = INITIAL_CAPACITY;
    }

    @Override
    public boolean add(E element) {
        if (checkCapacityLevel()) {
            restructuringOfInternalArray();
        }

        int index = getElementPosition(element);

        if (arr[index] == null) {
            arr[index] = new Entry<>(element, null);
            amountOfOccupiedCells++;
            amountOfElements++;
            return true;
        } else {
            if (arr[index].value.equals(element)) {
                return false;
            } else {
                if (isAddElementToInternalLinkedList(arr[index], element)) {
                    amountOfElements++;
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    @Override
    public boolean remove(E element) {
        int index = getElementPosition(element);
        Entry<E> entry = arr[index];

        if(entry == null) {
            return false;
        }

        if(entry.value.equals(element)) { //совпадает ли элемент в ячейке с переданным
            removeElementFromCell(index);
            return true;
        } else { // если нет, то проверяем связанный список в этой ячейке
            return removeElementsFromInternalLinkedList(entry, element);
        }
    }

    @Override
    public int size() {
        return this.amountOfElements;
    }

    @Override
    public void clear() {
        this.arr = new Entry[INITIAL_CAPACITY];
        this.amountOfOccupiedCells = 0;
        this.amountOfElements = 0;
        this.currentCapacity = INITIAL_CAPACITY;
    }

    @Override
    public boolean contains(E element) {
        int index = getElementPosition(element);
        Entry<E> entry = arr[index];

        while (true) {
            if(entry == null) {
                return false;
            } else if (entry.value.equals(element)) {
                return true;
            } else {
                entry = entry.next;
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int countOfElements = 0;//счетчик элементов
            int arrayIndex = 0;//индекс ячейки массива
            Entry<E> entry;

            @Override
            public boolean hasNext() {
                return countOfElements < amountOfElements;
            }

            @Override
            public E next() {

                while (arr[arrayIndex] == null) {//пока ячейка пуста,
                    arrayIndex++;//идем в следующую
                }

                if(entry == null) {//если энтри еще не проинициализирована
                    entry = arr[arrayIndex];// присваиваем ей значение
                }

                E result = entry.value;//вынимаем значение из энтри
                entry = entry.next;//переписываем значение энтри на следующее в цепочке

                if(entry == null) {//если энтри стала null, т.е в цепочке не элементов больше
                    arrayIndex++;// меняем индекс ячейки
                }

                countOfElements++;//увеличиваем счетчик элементов

                return result;
            }
        };
    }

    private boolean checkCapacityLevel() {
        return (float) amountOfOccupiedCells / currentCapacity >= LOAD_FACTOR;
    }

    private int getElementPosition(E element) {
        if(element == null) {
            throw new NullPointerException();
        }
        int hashCode = Math.abs(element.hashCode());
        return hashCode % currentCapacity;
    }


    /**
     * Метод увеличивает размер внутреннего массива в 2 раза, пересчитывает индексы всех элементов и размещает элементы в новом массиве
     */
    private void restructuringOfInternalArray() {
        this.amountOfOccupiedCells = 0;
        this.currentCapacity *= 2;
        Entry<E>[] newArr = new Entry[currentCapacity];
        for (Entry<E> element : this.arr) {
            if (element != null) {
                int index = getElementPosition(element.value);

                if (newArr[index] == null) {
                    newArr[index] = element;
                    this.amountOfOccupiedCells++;
                } else { // ячейка занята, проход по вложенному связанному списку, вставка к последнему эл-ту в поле next
                    addElementToInternalLinkedList(newArr[index], element);
                }
                //перестановка всех эл-тов связанного списка текущего элемента на новые позиции в увеличенном массиве
                addElementToInternalLinkedListIfEntryHasNextElement(element, newArr);
            }
        }
        this.arr = newArr;
    }


    /**
     * Метод добавляет элемент в поле next переданного объекта Entry при (!)реструктуризации массива, либо в поле next след.элемента и т.д
     * На equals элементы не проверяются,т.к преобразуется внутренний массив, который содержить только уникальные элементы.
     *
     * @param entry   - объект Entry, поле next которого нужно проверить и вставить туда переданный E element
     * @param element - элемент для вставки в поле next
     */
    private void addElementToInternalLinkedList(Entry<E> entry, Entry<E> element) {
        if (entry.next == null) {
            entry.next = element;
            return;
        }

        addElementToInternalLinkedList(entry.next, element);
    }

    /**
     * Метод вставляет элементы связанного списка переданного объекта Entry в новый увеличенный массив при (!)реструктуризации массива.
     * На equals элементы не проверяются,т.к преобразуется внутренний массив, который содержить только уникальные элементы.
     *
     * @param element - Entry, внутренние элементы которого итерируются
     * @param newArr  - новый увеличенный массив для вставки
     */
    private void addElementToInternalLinkedListIfEntryHasNextElement(Entry<E> element, Entry<E>[] newArr) {
        Entry<E> next = element.next;

        if (next == null) {
            return;
        }

        element.next = null;

        int index = getElementPosition(next.value);

        if (newArr[index] == null) {
            newArr[index] = next;
            this.amountOfOccupiedCells++;
        } else {
            addElementToInternalLinkedList(newArr[index], next);
        }

        addElementToInternalLinkedListIfEntryHasNextElement(next, newArr);
    }

    /**
     * Метод добавляет новый элемент в поле next переданного объекта Entry, либо в поле след-го эл-та, у которого данное поле равно null.
     *
     * @param entry   - объект Entry, поле next которого нужно проверить и вставить туда переданный E element
     * @param element - элемент для вставки в поле next
     * @return - true - если объект добавлен, false - если найден дубль, вставка не производится
     */
    private boolean isAddElementToInternalLinkedList(Entry<E> entry, E element) {
        if (entry.next == null) {
            entry.next = new Entry<>(element, null);
            return true;
        }

        if (entry.next.value.equals(element)) {
            return false;
        }

        return isAddElementToInternalLinkedList(entry.next, element);
    }

    private void removeElementFromCell(int index) {
        if (arr[index].next != null) {
            arr[index] = arr[index].next;
        } else {
            arr[index] = null;
            amountOfOccupiedCells--;
        }
        this.amountOfElements--;
    }

    private boolean removeElementsFromInternalLinkedList(Entry<E> entry, E element) {
        Entry<E> next = entry.next;

        if(next == null) {
            return false;
        }

        if (next.value.equals(element)) {
            if(next.next != null) {
                entry.next = next.next;
            } else {
                entry.next = null;
            }
            amountOfElements--;
            return true;
        }
        return removeElementsFromInternalLinkedList(next, element);
    }


    private static class Entry<E> {
        private E value;
        Entry<E> next;
        public Entry(E value, Entry<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
