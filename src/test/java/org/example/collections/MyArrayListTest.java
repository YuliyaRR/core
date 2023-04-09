package org.example.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class MyArrayListTest {
    @Test
    public void size(){
        MyArrayList<String> array = new MyArrayList<>();
        int size = array.size();
        Assertions.assertEquals(0, size);
    }

    @Test
    public void add(){
        MyArrayList<String> array = new MyArrayList<>();
        array.add("A");
        Assertions.assertEquals(1, array.size());
    }
    @Test
    public void addAndIncrease(){
        MyArrayList<String> array = new MyArrayList<>();
        for (int i = 0; i < 11; i++) {
            array.add("A" + i);
        }
        Assertions.assertEquals(11, array.size());
    }
    @Test
    public void addByIndex(){
        MyArrayList<String> array = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            array.add("A" + i);
        }
        array.add(3, "B");
        Assertions.assertEquals(6, array.size());
    }

    @Test
    public void addByIndex2(){
        MyArrayList<String> array = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            array.add("A" + i);
        }
        array.add(3, "B");
        Assertions.assertEquals("B", array.get(3));
    }

    @Test
    public void removeByIndex(){
        MyArrayList<String> array = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            array.add("A" + i);
        }
        array.remove(3);
        Assertions.assertEquals(4, array.size());
    }

    @Test
    public void removeByObject(){
        MyArrayList<String> array = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            array.add("A" + i);
        }

        array.remove("A1");

        Assertions.assertEquals(4, array.size());
    }
    @Test
    public void clean(){
        MyArrayList<String> array = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            array.add("A" + i);
        }

        for (int i = 0; i < 5; i++) {
            System.out.print(array.get(i) + " ");
        }

        System.out.println();

        array.clear();

        for (int i = 0; i < 5; i++) {
            System.out.print(array.get(i) + " ");
        }
        Assertions.assertEquals(0, array.size());
    }

    @Test
    public void trimToSize(){
        MyArrayList<String> array = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            array.add("A" + i);
        }

        array.remove(3);
        array.remove("A2");

        array.trimToSize();

        Assertions.assertEquals(3, array.size());
    }
    @Test
    public void sort() {
        MyArrayList<Integer> array = new MyArrayList<>();
        array.add(5);
        array.add(1);
        array.add(-5);
        array.add(8);
        array.add(14);
        array.add(0);
        array.add(16);
        array.add(2);

        array.sort(Comparator.comparingInt(Integer::intValue));

        MyArrayList<Integer> arrayExpected = new MyArrayList<>();
        arrayExpected.add(-5);
        arrayExpected.add(0);
        arrayExpected.add(1);
        arrayExpected.add(2);
        arrayExpected.add(5);
        arrayExpected.add(8);
        arrayExpected.add(14);
        arrayExpected.add(16);

        Assertions.assertEquals(array, arrayExpected);
    }

    @Test
    public void sort2() {
        MyArrayList<Integer> array = new MyArrayList<>();
        array.add(5);
        array.add(1);
        array.add(-5);
        array.add(8);
        array.add(14);
        array.add(0);
        array.add(16);
        array.add(2);

        array.sort(Comparator.comparingInt(Integer::intValue));

        MyArrayList<Integer> arrayExpected = new MyArrayList<>();
        arrayExpected.add(-5);
        arrayExpected.add(0);
        arrayExpected.add(1);
        arrayExpected.add(2);
        arrayExpected.add(10);
        arrayExpected.add(8);
        arrayExpected.add(14);
        arrayExpected.add(16);

        Assertions.assertNotEquals(array, arrayExpected);
    }

    @Test
    public void toStringTest() {
        MyArrayList<Integer> array = new MyArrayList<>();
        array.add(5);
        array.add(1);
        array.add(-5);
        array.add(8);

        String expected = "[5, 1, -5, 8]";

        Assertions.assertEquals(expected, array.toString());
    }


}
