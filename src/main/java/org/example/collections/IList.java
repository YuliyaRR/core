package org.example.collections;

import java.util.Comparator;

public interface IList <E> extends ICollection<E> {
    boolean add(E element);
    boolean add(int index, E element);
    E get (int index);
    boolean remove(int index);
    boolean remove(E element);
    int size();
    void clear();
    void sort(Comparator<? super E> comparator);
    boolean contains(E element);
}
