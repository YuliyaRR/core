package org.example.collections;

public interface ISet<E> extends ICollection<E>{
    boolean add(E element);
    boolean remove(E element);
    int size();
    void clear();
    boolean contains(E element);
}
