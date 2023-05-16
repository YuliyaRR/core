package org.example.collections;

import java.util.Comparator;
import java.util.Objects;

/* Permits all elements (including null);
 * Operations that index into the list will traverse the list from the beginning or the end,
 * whichever is closer to the specified index.
 */
public class MyLinkedList<E> implements IList<E>{
    private Node<E> first;
    private Node<E> last;
    private int size;

    /*Вставка в конец списка*/
    @Override
    public boolean add(E element) {
        if (this.size == 0) {
            Node<E> node = new Node<>(null, element, null);
            this.first = node;
            this.last = node;
        } else {
            Node<E> secondLastNode = this.last;
            Node<E> node = new Node<>(secondLastNode, element, null);
            secondLastNode.next = node;
            this.last = node;
        }
        size++;
        return true;
    }

    /*Вставка на любую позицию в списке*/
    @Override
    public boolean add(int index, E element) {
        if(index > this.size || index < 0){
            throw new IndexOutOfBoundsException();
        } else if(index == this.size) {
            add(element);
        } else {
            Node<E> currentNodeByIndex = getByIndex(index);
            Node<E> previousCurrentNodeByIndex = currentNodeByIndex.previous;//will be null, if it is the first element in the collection
            Node<E> node = new Node<>(previousCurrentNodeByIndex, element, currentNodeByIndex);
            currentNodeByIndex.previous = node;
            if (previousCurrentNodeByIndex != null) {
                previousCurrentNodeByIndex.next = node;
            } else {
                this.first = node;
            }
            size++;
        }
        return true;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return getByIndex(index).value;
    }

    @Override
    public boolean remove(int index) {
        checkIndex(index);

        Node<E> currentNodeByIndex = getByIndex(index);
        Node<E> previousCurrentNodeByIndex = currentNodeByIndex.previous;
        Node<E> nextCurrentNodeByIndex = currentNodeByIndex.next;

        if (previousCurrentNodeByIndex != null) {
            if(nextCurrentNodeByIndex != null) {
                previousCurrentNodeByIndex.next = nextCurrentNodeByIndex;//+вставка в середину
                nextCurrentNodeByIndex.previous = previousCurrentNodeByIndex;//+вставка в середину
            } else {//удаляется последний элемент в списке
                this.last = previousCurrentNodeByIndex;
                previousCurrentNodeByIndex.next = null;
            }
        } else {//удаляется первый элемент в списке
            if(nextCurrentNodeByIndex != null) {
                this.first = nextCurrentNodeByIndex;
                nextCurrentNodeByIndex.previous = null;
            }
        }

        currentNodeByIndex = null;

        size--;

        return true;
    }

    @Override
    public boolean remove(E element) {
        if(this.first == null) {
            return false;
        }

        Node<E> currectNode = this.first;

        for (int i = 0; i < this.size; i++) {
            if(currectNode.value.equals(element)) {
                remove(i);
                return true;
            }
            currectNode = currectNode.next;
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (Node<E> curr = this.first; curr != null; ) {
            Node<E> next = curr.next;
            curr.previous = null;
            curr.next = null;
            curr.value = null;
            curr = next;
        }
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {

    }

    @Override
    public boolean contains(E element) {
        for (Node<E> node = first; node != null; node = node.next) {
            if(Objects.equals(node.value, element)) {
                return true;
            }
        }
        return false;
    }

    private void checkIndex(int index) {
        if(index >= this.size || index < 0){
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<E> getByIndex(int index) {
        int indexOfMiddle = this.size / 2;
        Node<E> node;
        if(index >= indexOfMiddle) { //from tail to head
            node = this.last;
            for (int i = size - 1; i != index ; i--) {
                node = node.previous;
            }
        } else { //from head to tail
            node = this.first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }

        }
        return node;
    }

    @Override
    public String toString() {
        if(this.size == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();
        Node<E> currNode = this.first;

        builder.append("[");

        for (int i = 0; i < this.size(); i++) {
            if(i != this.size() - 1) {
                builder.append(currNode.value).append(", ");
                currNode = currNode.next;
            } else {
                builder.append(currNode.value).append("]");
            }
        }

        return builder.toString();
    }

    private static class Node<E> {
        private Node<E> previous;
        private E value;
        private Node<E> next;

        public Node(Node<E> previous, E value, Node<E> next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
