package org.example.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class MyArrayListTest {
    @Test
    public void size(){
        IList<String> list = new MyArrayList<>();
        int size = list.size();
        Assertions.assertEquals(0, size);
    }

    @Test
    public void add(){
        IList<String> list = new MyArrayList<>();
        list.add("A");
        Assertions.assertEquals(1, list.size());
    }
    @Test
    public void addAndIncrease(){
        IList<String> list = new MyArrayList<>();
        for (int i = 0; i < 11; i++) {
            list.add("A" + i);
        }
        Assertions.assertEquals(11, list.size());
    }
    @Test
    public void addByIndex(){
        IList<String> list = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("A" + i);
        }
        list.add(3, "B");
        Assertions.assertEquals(6, list.size());
    }

    @Test
    public void addByIndex2(){
        IList<String> list = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("A" + i);
        }
        list.add(3, "B");
        Assertions.assertEquals("B", list.get(3));
    }

    @Test
    public void removeByIndex(){
        IList<String> list = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("A" + i);
        }
        list.remove(3);
        Assertions.assertEquals(4, list.size());
    }

    @Test
    public void removeByObject(){
        IList<String> list = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("A" + i);
        }

        list.remove("A1");

        Assertions.assertEquals(4, list.size());
    }
    @Test
    public void clean(){
        IList<String> list = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("A" + i);
        }

        list.clear();

        Assertions.assertEquals(0, list.size());
    }
    @Test
    public void sort() {
        IList<Integer> list = new MyArrayList<>();
        list.add(5);
        list.add(1);
        list.add(-5);
        list.add(8);
        list.add(14);
        list.add(0);
        list.add(16);
        list.add(2);

        list.sort(Comparator.comparingInt(Integer::intValue));


        IList<Integer> arrayExpected = new MyArrayList<>();
        arrayExpected.add(-5);
        arrayExpected.add(0);
        arrayExpected.add(1);
        arrayExpected.add(2);
        arrayExpected.add(5);
        arrayExpected.add(8);
        arrayExpected.add(14);
        arrayExpected.add(16);

        Assertions.assertEquals(list, arrayExpected);
    }

    @Test
    public void sort2() {
        IList<Integer> list = new MyArrayList<>();
        list.add(5);
        list.add(1);
        list.add(-5);
        list.add(8);
        list.add(14);
        list.add(0);
        list.add(16);
        list.add(2);

        list.sort(Comparator.comparingInt(Integer::intValue));

        IList<Integer> arrayExpected = new MyArrayList<>();
        arrayExpected.add(-5);
        arrayExpected.add(0);
        arrayExpected.add(1);
        arrayExpected.add(2);
        arrayExpected.add(10);
        arrayExpected.add(8);
        arrayExpected.add(14);
        arrayExpected.add(16);

        Assertions.assertNotEquals(list, arrayExpected);
    }

    @Test
    public void toStringTest() {
        IList<Integer> list = new MyArrayList<>();
        list.add(5);
        list.add(1);
        list.add(-5);
        list.add(8);

        String expected = "[5, 1, -5, 8]";

        Assertions.assertEquals(expected, list.toString());
    }

    @Test
    public void containsIsTrue() {
        IList<Integer> list = new MyArrayList<>();
        list.add(5);
        list.add(1);
        list.add(-5);
        list.add(8);

        Assertions.assertTrue(list.contains(5));
    }

    @Test
    public void containsIsFalse() {
        IList<Integer> list = new MyArrayList<>();
        list.add(5);
        list.add(1);
        list.add(-5);
        list.add(8);

        Assertions.assertFalse(list.contains(10));
    }

    @Test
    public void containsThrowsNPE() {
        IList<Integer> list = new MyArrayList<>();
        list.add(5);
        list.add(1);
        list.add(-5);
        list.add(8);

        Assertions.assertThrows(NullPointerException.class, () -> list.contains(null));
    }

    @Test
    public void forEachTest() {
        IList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(ThreadLocalRandom.current().nextInt(100));
        }
        int index = 0;
        for (Integer num: list) {
            index++;
        }
        Assertions.assertEquals(list.size(), index);
    }


}
