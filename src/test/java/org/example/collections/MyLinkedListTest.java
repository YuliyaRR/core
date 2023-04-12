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
        linkedList.add(3, "B");
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
    public void getElementByIndex2(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        Assertions.assertEquals("C", linkedList.get(2));
    }

    @Test
    public void getElementByWrongNegativeIndex(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(-10));
    }

    @Test
    public void getElementByIndexEqualsSize(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(1));
    }

    @Test
    public void getElementByWrongPositiveIndex(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(100));
    }

    @Test
    public void removeByIndexAssertSize(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        linkedList.remove(3);
        Assertions.assertEquals(4, linkedList.size());
    }

    @Test
    public void removeInMiddleByIndexAssertResultOfRemove(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 4; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertTrue(linkedList.remove(2));
    }

    @Test
    public void removeAtBeginningByIndexAssertResultOfRemove(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertTrue(linkedList.remove(0));
    }

    @Test
    public void removeAtEndByIndexAssertResultOfRemove(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertTrue(linkedList.remove(4));
    }

    @Test
    public void removeByIndexSingleElementAssertResultOfRemove(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        Assertions.assertTrue(linkedList.remove(0));
    }

    @Test
    public void removeByIndexEqualsSize(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(5));
    }

    @Test
    public void removeElementByWrongNegativeIndex(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(-10));
    }

    @Test
    public void removeElementByWrongPositiveIndex(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(100));
    }

    @Test
    public void removeByObjectAssertResultOfRemove(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertTrue(linkedList.remove("A1"));
    }

    @Test
    public void removeByObjectFromEmptyListAssertResultOfRemove(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        Assertions.assertFalse(linkedList.remove("A1"));
    }

    @Test
    public void removeByObjectNonexistentElementAssertResultOfRemove(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertFalse(linkedList.remove("D"));
    }

    @Test
    public void removeByObjectAssertSize(){
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
