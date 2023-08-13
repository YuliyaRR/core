package org.example.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class MyHashSetTest {
    @Test
    public void addSingleElementIsTrue(){
        ISet<Integer> hashSet = new MyHashSet<>();
        Assertions.assertTrue(hashSet.add(15));
    }

    @Test
    public void addDuplicateElementIsFalse(){
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(15);
        Assertions.assertFalse(hashSet.add(15));
    }

    @Test
    public void addElementWithTheSameIndexCheckRecursionFirstLoopIsTrue(){
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(15);
        Assertions.assertTrue(hashSet.add(31));
    }

    @Test
    public void addElementWithTheSameIndexCheckRecursionSecondLoopIsTrue(){
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(15);
        hashSet.add(31);
        Assertions.assertTrue(hashSet.add(47));
    }

    @Test
    public void addDuplicateElementWithTheSameIndexCheckRecursionSecondLoopIsTrue(){
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(15);
        hashSet.add(31);
        Assertions.assertFalse(hashSet.add(31));
    }

    @Test
    public void addManyElementsCheckIncreaseInternalArray(){
        ISet<Integer> hashSet = new MyHashSet<>();
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
        Assertions.assertTrue(hashSet.add(31));
    }

    @Test
    public void addManyElementsCheckRestructuringOfInternalArray(){
        ISet<Integer> hashSet = new MyHashSet<>();
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

        Assertions.assertTrue(hashSet.add(51));
    }

    @Test
    public void removeElementFromCellIsTrue() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        Assertions.assertTrue(hashSet.remove(2));
    }

    @Test
    public void removeElementFromCellCheckSize() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        hashSet.remove(2);

        Assertions.assertEquals(2, hashSet.size());
    }

    @Test
    public void removeNonExistElementFromCellIsFalse() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        Assertions.assertFalse(hashSet.remove(6));
    }

    @Test
    public void removeElementFromCellNextIsNotNull() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(15);
        hashSet.add(31);

        hashSet.remove(15);

        Assertions.assertEquals(1, hashSet.size());
    }

    @Test
    public void removeElementFromInternalLinkedListNextIsNull() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(15);
        hashSet.add(31);

        Assertions.assertTrue(hashSet.remove(31));
    }

    @Test
    public void removeElementFromInternalLinkedListCheckSize() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(15);
        hashSet.add(31);
        hashSet.remove(31);

        Assertions.assertEquals(1, hashSet.size());
    }

    @Test
    public void removeElementFromInternalLinkedListIs2() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(15);
        hashSet.add(31);
        hashSet.add(47);
        hashSet.remove(31);

        Assertions.assertEquals(2, hashSet.size());
    }

    @Test
    public void removeElementFromInternalLinkedListIs3() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(15);
        hashSet.add(31);
        hashSet.add(47);
        hashSet.remove(47);

        Assertions.assertEquals(2, hashSet.size());
    }

    @Test
    public void cleanCheckSize() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        hashSet.clear();

        Assertions.assertEquals(0, hashSet.size());
    }

    @Test
    public void containsIsTrue() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        Assertions.assertTrue(hashSet.contains(2));
    }

    @Test
    public void containsIsFalse() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);

        Assertions.assertFalse(hashSet.contains(12));
    }

    @Test
    public void containsIsTrueInternalLinkedList() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(15);
        hashSet.add(31);
        hashSet.add(47);

        Assertions.assertTrue(hashSet.contains(47));
    }

    @Test
    public void containsIsFalseInternalLinkedList() {
        ISet<Integer> hashSet = new MyHashSet<>();
        hashSet.add(15);
        hashSet.add(31);

        Assertions.assertFalse(hashSet.contains(47));
    }

    @Test
    public void forEachTest() {
        ISet<Integer> set = new MyHashSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(ThreadLocalRandom.current().nextInt(100));
        }
        int index = 0;
        for (Integer num: set) {
            index++;
        }
        Assertions.assertEquals(set.size(), index);
    }

}
