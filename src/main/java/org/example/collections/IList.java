package org.example.collections;

import java.util.Comparator;

public interface IList <E> {
    void add(E element);
    void add(int index, E element);
    E get (int index);
    boolean remove(int index);
    boolean remove(E element);
    int size();
    void clear();
    void trimToSize();
    void sort(Comparator<? super E> comparator);
}
