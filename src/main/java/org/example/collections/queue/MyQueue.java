package org.example.collections.queue;


public interface MyQueue <E> extends Iterable<E>{
    boolean add (E element);
    E peek();
    E poll();
    int size();
}
