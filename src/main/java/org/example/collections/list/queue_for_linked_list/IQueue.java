package org.example.collections.list.queue_for_linked_list;

import org.example.collections.ICollection;

public interface IQueue <E> extends ICollection<E> {
    boolean add(E element);
    E poll();
    E peek();
}
