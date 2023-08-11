package org.example.collections;

public interface ICollection<E> extends Iterable<E>{
    boolean add(E element);
    boolean remove(E element);
    int size();
    void clear();
    boolean contains(E element);
}
