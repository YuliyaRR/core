package org.example.collections.map;

import java.util.*;

public class MyHashMap <K,V> implements IMap<K,V> {
    private static final float LOAD_FACTOR = 0.75f;
    private static final int INITIAL_CAPACITY = 16;
    private Entry<K, V>[] arr;
    private int amountOfOccupiedCells;
    private int amountOfElements;
    private int currentCapacity;

    public MyHashMap() {
        this.arr = new Entry[INITIAL_CAPACITY];
        this.currentCapacity = INITIAL_CAPACITY;
    }

    @Override
    public void put(K key, V value) {

        if(checkCapacityLevel()) {
            increaseAndFillInternalArray();
        }

        int index = getElementPosition(key);
        Entry<K, V> entry = arr[index];

        if (entry == null) {
            arr[index] = new Entry<>(key, value, null);
            this.amountOfOccupiedCells++;
            this.amountOfElements++;
        } else {
            while (true) {
                if (entry.key.equals(key)) {//ключи совпали, переписываем значение
                    entry.value = value;
                    break;
                } else {//ключи не совпали, двигаемся по цепочке вниз связанного списка
                    if(entry.next == null) {
                        entry.next = new Entry<>(key, value, null);
                        this.amountOfElements++;
                        break;
                    } else {
                        entry = entry.next;
                    }
                }
            }
        }

    }

    @Override
    public V get(K key) {
        int index = getElementPosition(key);
        Entry<K, V> entry = arr[index];
        while (true) {
            if (entry == null) {
                return null;
            } else {
                if (Objects.equals(key, entry.key)) {
                    return entry.value;
                } else {
                    entry = entry.next;
                }
            }
        }
    }


    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Entry<K, V> entry : arr) {
            if(entry != null) {
                set.add(entry.key);
                //проверка вложенного списка
                while (entry.next != null){
                    set.add(entry.next.key);
                    entry = entry.next;
                }
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        List<V> list = new ArrayList<>();
        for (Entry<K, V> entry : arr) {
            if(entry != null) {
                list.add(entry.value);
                //проверка вложенного списка
                while (entry.next != null){
                    list.add(entry.next.value);
                    entry = entry.next;
                }
            }
        }
        return list;
    }

    @Override
    public boolean remove(K key) {
        int index = getElementPosition(key);
        Entry<K, V> entry = arr[index];
        if (entry == null) {//если элемент не найден (ячейка пуста)
            return false;
        } else { //ячейка заполнена
            if (Objects.equals(key, entry.key)) { //если исходный ключ равен ключу в ячейке
                removeElementFromCell(index);
                return true;
            } else {//если исходный ключ НЕ равен ключу в ячейке, нужно проверять связанные элементы
                return removeElementFromInternalLinkedList(entry, key);
            }
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

    /**
     * Метод проверяет загруженность внутреннего массива
     * @return true - если занято 75% и более ячеек внутреннего массива,
     *         false - если занято меньше 75% ячеек
     */
    private boolean checkCapacityLevel() {
        return (float) this.amountOfOccupiedCells / this.currentCapacity >= LOAD_FACTOR;
    }

    /**
     * Метод увеличивает размер внутреннего массива в 2 раза, пересчитывает индексы всех элементов и размещает элементы в новом массиве
     */
    private void increaseAndFillInternalArray(){
        this.amountOfOccupiedCells = 0;
        this.currentCapacity *= 2;
        Entry<K, V>[] newArr = new Entry[currentCapacity];
        for (Entry<K, V> element : this.arr) {
            if(element != null) {
                setEntry(element, newArr);

                restructuringOfInternalLinkedListElements(element, newArr);
            }
        }
        this.arr = newArr;
    }

    /**
     * Метод определяет индекс ячейки внутреннего массива, в которую будет вставлен элемент
     * @param key - ключ элемента, по хэш коду которого определяется индекс ячейки
     * @return - индекс ячейки внутреннего массива
     */
    private int getElementPosition(K key) {
        int hashCode = Math.abs(key.hashCode());
        return hashCode % this.currentCapacity;
    }


    /**
     * Метод определяет позицию для вставки объекта Entry при (!)реструктуризации внутреннего массива (ячейка или связанный
     * список объекта Entry, если ячейка зянята другим элементом).
     * @param element - элемент, который нужно добавить в увеличенный массив
     * @param newArr - новый увеличенный массив для вставки элементов
     */
    private void setEntry(Entry<K,V> element, Entry<K, V>[]newArr) {
        int index = getElementPosition(element.key);

        if(newArr[index] == null) {
            newArr[index] = element;
            this.amountOfOccupiedCells++;
        } else {
            addElementToInternalLinkedList(newArr[index], element);
        }
    }

    /**
     * Метод добавляет элемент в поле next переданного объекта Entry при (!)реструктуризации массива, либо в поле next след.элемента и т.д
     * На equals элементы не проверяются,т.к преобразуется внутренний массив, который содержить только уникальные элементы.
     *
     * @param entryInThisCell   - объект Entry, поле next которого нужно проверить и вставить туда переданный E element
     * @param elementToAdd - элемент для вставки в поле next
     */
    private void addElementToInternalLinkedList (Entry<K, V> entryInThisCell, Entry<K, V> elementToAdd) {
        if(entryInThisCell.next == null) {
            entryInThisCell.next = elementToAdd;
            return;
        }

        addElementToInternalLinkedList(entryInThisCell.next, elementToAdd);

    }

    /**
     * Метод вставляет элементы связанного списка переданного объекта Entry в новый увеличенный массив при (!)реструктуризации массива.
     * На equals элементы не проверяются,т.к преобразуется внутренний массив, который содержить только уникальные элементы.
     * @param element - элемент, который нужно добавить в увеличенный массив
     * @param newArr - новый увеличенный массив для вставки элементов
     */
    private void restructuringOfInternalLinkedListElements(Entry<K,V> element, Entry<K, V>[]newArr) {
       Entry<K, V> next = element.next;

        if(next == null) {
            return;
        }

        element.next = null;

        setEntry(next, newArr);

        restructuringOfInternalLinkedListElements(next, newArr);
    }

    /**
     * Метод удаляет объект Entry из ячейки внутреннего массива
     * @param index - индекс ячейки внутреннего массива, в которой находится объект Entry
     */
    private void removeElementFromCell(int index) {
        Entry<K, V> nextEntry = arr[index].next;
        if(nextEntry == null) { //при отсутствии next-элемента обнуляем ячейку
            arr[index] = null;
            this.amountOfOccupiedCells--;
        } else { //если next-элемент есть, то записываем его в ячейку
            arr[index] = nextEntry;
        }

        this.amountOfElements--;
    }

    /**
     * Метод удаляет объект Entry из связанног списка переданного объекта Entry
     * @param entry - объект Entry, связанный список которого необходимо проверить
     * @param key - ключ объекта, который необходимо удалить
     * @return true - если объект был найден и удален,
     *         false - если объект с таким ключом не найден
     */
    private boolean removeElementFromInternalLinkedList(Entry<K,V> entry, K key) {
        Entry<K, V> nextEntry = entry.next;

        if(nextEntry == null) {
            return false;
        }

        if (Objects.equals(key, nextEntry.key)) {//ключи совпали
            if (nextEntry.next == null) { //если нет next-элемента, обнуляем поле next у entry
                entry.next = null;
            } else { //если next-элемент есть, то записываем его в поле next у entry
                entry.next = nextEntry.next;
            }

            this.amountOfElements--;
            return true;

        }

        return removeElementFromInternalLinkedList(nextEntry, key);
    }


    private static class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K,V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
