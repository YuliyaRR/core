package org.example.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashSetTest {
    private ISet<Integer> hashSet;
    @BeforeEach
    public void setUp() {
        this.hashSet = new MyHashSet<>();
    }

    @Test
    public void addSingleElementIsTrue(){
       assertTrue(hashSet.add(15));
    }

    @Test
    public void addDuplicateElementIsFalse(){
        hashSet.add(15);
        assertFalse(hashSet.add(15));
    }

    @Test
    public void addElementWithTheSameIndexCheckRecursionFirstLoopIsTrue(){
        hashSet.add(15);
        assertTrue(hashSet.add(31));
    }

    @Test
    public void addElementWithTheSameIndexCheckRecursionSecondLoopIsTrue(){
        hashSet.add(15);
        hashSet.add(31);
        assertTrue(hashSet.add(47));
    }

    @Test
    public void addDuplicateElementWithTheSameIndexCheckRecursionSecondLoopIsTrue(){
        hashSet.add(15);
        hashSet.add(31);
        assertFalse(hashSet.add(31));
    }

    @Test
    public void addManyElementsCheckIncreaseInternalArray(){//?
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(6);
        hashSet.add(7);
        hashSet.add(8);
        hashSet.add(9);
        hashSet.add(10);
        hashSet.add(11);
        hashSet.add(12);
        assertTrue(hashSet.add(31));
    }

    @Test
    public void addManyElementsCheckRestructuringOfInternalArray(){
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(23); //связанный список для 7
        hashSet.add(19);//связанный список для 3
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(6);
        hashSet.add(7);
        hashSet.add(8);
        hashSet.add(9);
        hashSet.add(10);
        hashSet.add(11);
        hashSet.add(12);

        assertTrue(hashSet.add(51));
    }

    @Test
    public void removeElementFromCellIsTrue() {
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        assertTrue(hashSet.remove(2));
    }

    @Test
    public void removeElementFromCellCheckSize() {
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        hashSet.remove(2);

        assertEquals(2, hashSet.size());
    }

    @Test
    public void removeNonExistElementFromCellIsFalse() {
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        assertFalse(hashSet.remove(6));
    }

    @Test
    public void removeElementFromCellNextIsNotNull() {
        hashSet.add(15);
        hashSet.add(31);

        hashSet.remove(15);

        assertEquals(1, hashSet.size());
    }

    @Test
    public void removeElementFromInternalLinkedListNextIsNull() {
        hashSet.add(15);
        hashSet.add(31);

        assertTrue(hashSet.remove(31));
    }

    @Test
    public void removeElementFromInternalLinkedListCheckSize() {
        hashSet.add(15);
        hashSet.add(31);
        hashSet.remove(31);

        assertEquals(1, hashSet.size());
    }

    @Test
    public void removeElementFromInternalLinkedListIs2() {
        hashSet.add(15);
        hashSet.add(31);
        hashSet.add(47);
        hashSet.remove(31);

        assertEquals(2, hashSet.size());
    }

    @Test
    public void removeElementFromInternalLinkedListIs3() {
        hashSet.add(15);
        hashSet.add(31);
        hashSet.add(47);
        hashSet.remove(47);

        assertEquals(2, hashSet.size());
    }

    @Test
    public void cleanCheckSize() {
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        hashSet.clear();

        assertEquals(0, hashSet.size());
    }

    @Test
    public void containsIsTrue() {
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        assertTrue(hashSet.contains(2));
    }

    @Test
    public void containsIsFalse() {
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        assertFalse(hashSet.contains(12));
    }

    @Test
    public void containsIsTrueInternalLinkedList() {
        hashSet.add(15);
        hashSet.add(31);
        hashSet.add(47);

        assertTrue(hashSet.contains(47));
    }

    @Test
    public void containsIsFalseInternalLinkedList() {
        hashSet.add(15);
        hashSet.add(31);

        assertFalse(hashSet.contains(47));
    }

    @Test
    public void forEachTest() {
        for (int i = 0; i < 10; i++) {
            hashSet.add(ThreadLocalRandom.current().nextInt(100));
        }
        int index = 0;
        for (Integer num: hashSet) {
            index++;
        }
        assertEquals(hashSet.size(), index);
    }

}
