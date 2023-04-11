package org.example.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyLinkedListTest {
    @Test
    public void size(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        int size = linkedList.size();
        Assertions.assertEquals(0, size);
    }

    @Test
    public void addOneElement(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        Assertions.assertEquals(1, linkedList.size());
    }

    @Test
    public void addManyElements(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 11; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertEquals(11, linkedList.size());
    }

    @Test
    public void addByIndexAssertSize(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        System.out.println(linkedList);
        linkedList.add(3, "B");
        System.out.println(linkedList);
        Assertions.assertEquals(6, linkedList.size());
    }

    @Test
    public void addByIndexAssertElement(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        linkedList.add(3, "B");
        Assertions.assertEquals("B", linkedList.get(3));
    }

    @Test
    public void addByIndexInEmptyList(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add(0, "B");
        Assertions.assertEquals("B", linkedList.get(0));
    }

    @Test
    public void addByWrongNegativeIndexInEmptyList(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.add(-1, "B"));
    }

    @Test
    public void addByWrongPositiveIndexInEmptyList(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.add(100, "B"));
    }

    @Test
    public void getElementByIndex(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        Assertions.assertEquals("A", linkedList.get(0));
    }

    @Test
    public void getElementByWrongNegativeIndex(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(-10));
    }

    @Test
    public void removeByIndex(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        linkedList.remove(3);
        Assertions.assertEquals(4, linkedList.size());
    }

    @Test
    public void removeByObject(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }

        linkedList.remove("A1");

        Assertions.assertEquals(4, linkedList.size());
    }

    @Test
    public void clean(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }

        linkedList.clear();

        Assertions.assertEquals(0, linkedList.size());
    }
}
