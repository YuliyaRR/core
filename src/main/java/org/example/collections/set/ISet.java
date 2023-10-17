package org.example.collections.set;

import org.example.collections.ICollection;

public interface ISet<E> extends ICollection<E> {
    boolean add(E element);
    boolean remove(E element);
    int size();
    void clear();
    boolean contains(E element);
}
