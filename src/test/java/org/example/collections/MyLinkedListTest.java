package org.example.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyLinkedListTest {
    @Test
    public void size(){
        IList<String> linkedList = new MyLinkedList<>();
        int size = linkedList.size();
        Assertions.assertEquals(0, size);
    }

    @Test
    public void addOneElement(){
        IList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        Assertions.assertEquals(1, linkedList.size());
    }

    @Test
    public void addManyElements(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 11; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertEquals(11, linkedList.size());
    }

    @Test
    public void addByIndexAssertSize(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        linkedList.add(3, "B");
        Assertions.assertEquals(6, linkedList.size());
    }

    @Test
    public void addByIndexAssertElement(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        linkedList.add(3, "B");
        Assertions.assertEquals("B", linkedList.get(3));
    }

    @Test
    public void addByIndexInEmptyList(){
        IList<String> linkedList = new MyLinkedList<>();
        linkedList.add(0, "B");
        Assertions.assertEquals("B", linkedList.get(0));
    }

    @Test
    public void addByWrongNegativeIndexInEmptyList(){
        IList<String> linkedList = new MyLinkedList<>();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.add(-1, "B"));
    }

    @Test
    public void addByWrongPositiveIndexInEmptyList(){
        IList<String> linkedList = new MyLinkedList<>();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.add(100, "B"));
    }

    @Test
    public void getElementByIndex(){
        IList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        Assertions.assertEquals("A", linkedList.get(0));
    }

    @Test
    public void getElementByIndex2(){
        IList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        Assertions.assertEquals("C", linkedList.get(2));
    }

    @Test
    public void getElementByWrongNegativeIndex(){
        IList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(-10));
    }

    @Test
    public void getElementByIndexEqualsSize(){
        IList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(1));
    }

    @Test
    public void getElementByWrongPositiveIndex(){
        IList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(100));
    }

    @Test
    public void removeByIndexAssertSize(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        linkedList.remove(3);
        Assertions.assertEquals(4, linkedList.size());
    }

    @Test
    public void removeInMiddleByIndexAssertResultOfRemove(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 4; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertTrue(linkedList.remove(2));
    }

    @Test
    public void removeAtBeginningByIndexAssertResultOfRemove(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertTrue(linkedList.remove(0));
    }

    @Test
    public void removeAtEndByIndexAssertResultOfRemove(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertTrue(linkedList.remove(4));
    }

    @Test
    public void removeByIndexSingleElementAssertResultOfRemove(){
        IList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        Assertions.assertTrue(linkedList.remove(0));
    }

    @Test
    public void removeByIndexEqualsSize(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(5));
    }

    @Test
    public void removeElementByWrongNegativeIndex(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(-10));
    }

    @Test
    public void removeElementByWrongPositiveIndex(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(100));
    }

    @Test
    public void removeByObjectAssertResultOfRemove(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertTrue(linkedList.remove("A1"));
    }

    @Test
    public void removeByObjectFromEmptyListAssertResultOfRemove(){
        IList<String> linkedList = new MyLinkedList<>();
        Assertions.assertFalse(linkedList.remove("A1"));
    }

    @Test
    public void removeByObjectNonexistentElementAssertResultOfRemove(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertFalse(linkedList.remove("D"));
    }

    @Test
    public void removeByObjectAssertSize(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        linkedList.remove("A1");
        Assertions.assertEquals(4, linkedList.size());
    }

    @Test
    public void clean(){
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        linkedList.clear();
        Assertions.assertEquals(0, linkedList.size());
    }

    @Test
    public void containsIsTrue() {
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertTrue(linkedList.contains("A3"));
    }

    @Test
    public void containsIsFalse() {
        IList<String> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add("A" + i);
        }
        Assertions.assertFalse(linkedList.contains("10"));
    }

    @Test
    public void containsNullIsTrueIfListContainsNull() {
        IList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add(null);
        linkedList.add("C");
        Assertions.assertTrue(linkedList.contains(null));
    }

    @Test
    public void containsNullIsFalseIfListDoesNotContainsNull() {
        IList<String> linkedList = new MyLinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        Assertions.assertFalse(linkedList.contains(null));
    }
}
