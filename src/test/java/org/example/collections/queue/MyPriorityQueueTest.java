package org.example.collections.queue;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class MyPriorityQueueTest {
    @Test
    public void add4ComparableElementsWithoutIncreaseArrayThenReturnTrueAndCheckSize() {
        MyQueue<Integer> queue = new MyPriorityQueue<>();
        assertTrue(queue.add(15));
        queue.add(3);
        queue.add(16);
        queue.add(10);
        int expSize = 4;
        String exp = "[3, 10, 16, 15]";
        assertEquals(exp, queue.toString());
        assertEquals(expSize, queue.size());
    }

    @Test
    public void add4ElementsWithComparatorWithoutIncreaseArrayThenReturnTrueAndCheckSize() {
        MyQueue<Integer> queue = new MyPriorityQueue<>(Integer::compare);
        assertTrue(queue.add(15));
        queue.add(3);
        queue.add(16);
        queue.add(10);
        int expSize = 4;
        String exp = "[3, 10, 16, 15]";
        assertEquals(exp, queue.toString());
        assertEquals(expSize, queue.size());
    }

    @Test
    public void add9ComparableElementsWithIncreaseArray() {
        MyQueue<Integer> queue = new MyPriorityQueue<>();
        queue.add(15);
        queue.add(3);
        queue.add(16);
        queue.add(10);
        queue.add(7);
        queue.add(-16);
        queue.add(100);
        queue.add(18);
        queue.add(0);
        String exp = "[-16, 0, 3, 7, 10, 16, 100, 18, 15]";
        assertEquals(exp, queue.toString());
    }

    @Test
    public void add9ElementsWithComparatorWithIncreaseArray() {
        MyQueue<Integer> queue = new MyPriorityQueue<>(Integer::compare);
        queue.add(15);
        queue.add(3);
        queue.add(16);
        queue.add(10);
        queue.add(7);
        queue.add(-16);
        queue.add(100);
        queue.add(18);
        queue.add(0);
        String exp = "[-16, 0, 3, 7, 10, 16, 100, 18, 15]";
        assertEquals(exp, queue.toString());
    }

    @Test
    public void add9ComparableElementsAndSiftUpThroughLeftBranch() {
        MyQueue<Integer> queue = new MyPriorityQueue<>();
        queue.add(10);
        queue.add(17);
        queue.add(21);
        queue.add(50);
        queue.add(39);
        queue.add(75);
        queue.add(40);
        queue.add(60);
        queue.add(15);
        String exp = "[10, 15, 21, 17, 39, 75, 40, 60, 50]";
        assertEquals(exp, queue.toString());
    }

    @Test
    public void add9ElementsWithComparatorAndSiftUpThroughLeftBranch() {
        MyQueue<Integer> queue = new MyPriorityQueue<>(Integer::compare);
        queue.add(10);
        queue.add(17);
        queue.add(21);
        queue.add(50);
        queue.add(39);
        queue.add(75);
        queue.add(40);
        queue.add(60);
        queue.add(15);
        String exp = "[10, 15, 21, 17, 39, 75, 40, 60, 50]";
        assertEquals(exp, queue.toString());
    }

    @Test
    public void add7ComparableElementsAndSiftUpThroughRightBranch() {
        MyQueue<Integer> queue = new MyPriorityQueue<>();
        queue.add(3);
        queue.add(10);
        queue.add(16);
        queue.add(15);
        queue.add(41);
        queue.add(20);
        queue.add(-5);
        String exp = "[-5, 10, 3, 15, 41, 20, 16]";
        assertEquals(exp, queue.toString());
    }

    @Test
    public void add7ElementsWithComparatorAndSiftUpThroughRightBranch() {
        MyQueue<Integer> queue = new MyPriorityQueue<>(Integer::compare);
        queue.add(3);
        queue.add(10);
        queue.add(16);
        queue.add(15);
        queue.add(41);
        queue.add(20);
        queue.add(-5);
        String exp = "[-5, 10, 3, 15, 41, 20, 16]";
        assertEquals(exp, queue.toString());
    }

    @Test
    public void addNullThenThrowNPE() {
        MyQueue<Integer> queue = new MyPriorityQueue<>();
        NullPointerException exception = assertThrows(NullPointerException.class, () -> queue.add(null));
        assertEquals("Null isn't supported in this collection", exception.getMessage());
    }

    @Test
    public void addNonComparableElementThenThrowCCE() {
        MyQueue<CatNonComparable> queue = new MyPriorityQueue<>();
        assertThrows(ClassCastException.class, () -> queue.add(new CatNonComparable()));
    }

    @Test
    public void peekWhenQueueNotEmptyThenShowFirstElementWithoutDelete() {
        MyQueue<Integer> queue = new MyPriorityQueue<>();
        queue.add(15);
        queue.add(3);
        queue.add(16);
        queue.add(10);

        Integer expElement = 3;
        int expSize = 4;

        assertEquals(expElement, queue.peek());
        assertEquals(expSize, queue.size());
    }

    @Test
    public void peekWhenQueueIsEmptyThenReturnNull() {
        MyQueue<Integer> queue = new MyPriorityQueue<>();
        int expSize = 0;
        assertEquals(expSize, queue.size());
        assertNull(queue.peek());
    }

    @Test
    public void pollWhenQueueNotEmptyThenShowFirstElementAndDeleteIt() {
        MyQueue<Integer> queue = new MyPriorityQueue<>();
        queue.add(15);
        queue.add(3);
        queue.add(16);
        queue.add(10);

        Integer expElement = 3;
        int expSize = 3;
        String expView = "[10, 15, 16]";

        assertEquals(expElement, queue.poll());
        assertEquals(expSize, queue.size());
        assertEquals(expView, queue.toString());
    }

    @Test
    public void pollWhenQueueHas9ComparableElementsAndSiftDownThroughRightBranch() {
       MyQueue<Integer> queue = new MyPriorityQueue<>();
        queue.add(6);
        queue.add(17);
        queue.add(10);
        queue.add(50);
        queue.add(39);
        queue.add(21);
        queue.add(40);
        queue.add(60);
        queue.add(75);
        Integer exp = 6;
        String expView = "[10, 17, 21, 50, 39, 75, 40, 60]";
        assertEquals(exp, queue.poll());
        assertEquals(expView, queue.toString());
    }

    @Test
    public void pollWhenQueueHas9ComparableElementsAndSiftDownThroughLeftBranch() {
        MyQueue<Integer> queue = new MyPriorityQueue<>();
        queue.add(6);
        queue.add(10);
        queue.add(17);
        queue.add(50);
        queue.add(69);
        queue.add(21);
        queue.add(40);
        queue.add(60);
        queue.add(53);
        Integer exp = 6;
        String expView = "[10, 50, 17, 53, 69, 21, 40, 60]";
        assertEquals(exp, queue.poll());
        assertEquals(expView, queue.toString());
    }

    @Test
    public void pollWhenQueueHas9ElementsWithComparatorAndSiftDownThroughRightBranch() {
        MyQueue<Integer> queue = new MyPriorityQueue<>(Integer::compare);
        queue.add(6);
        queue.add(17);
        queue.add(10);
        queue.add(50);
        queue.add(39);
        queue.add(21);
        queue.add(40);
        queue.add(60);
        queue.add(75);
        Integer exp = 6;
        String expView = "[10, 17, 21, 50, 39, 75, 40, 60]";
        assertEquals(exp, queue.poll());
        assertEquals(expView, queue.toString());
    }

    @Test
    public void pollWhenQueueHas9ElementsWithComparatorAndSiftDownThroughLeftBranch() {
        MyQueue<Integer> queue = new MyPriorityQueue<>(Integer::compare);
        queue.add(6);
        queue.add(10);
        queue.add(17);
        queue.add(50);
        queue.add(69);
        queue.add(21);
        queue.add(40);
        queue.add(60);
        queue.add(53);
        Integer exp = 6;
        String expView = "[10, 50, 17, 53, 69, 21, 40, 60]";
        assertEquals(exp, queue.poll());
        assertEquals(expView, queue.toString());
    }

    @Test
    public void pollWhenQueueIsEmptyThenReturnNull() {
        MyQueue<Integer> queue = new MyPriorityQueue<>();
        int expSize = 0;
        assertEquals(expSize, queue.size());
        assertNull(queue.poll());
    }

    @Test
    public void peekComparatorReversOrder() {
        MyQueue<Integer> queue = new MyPriorityQueue<>(Comparator.reverseOrder());
        queue.add(10);
        queue.add(20);
        assertEquals(20, queue.peek());
    }


}
