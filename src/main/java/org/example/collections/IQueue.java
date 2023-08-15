package org.example.collections;

import java.util.Queue;

public interface IQueue <E> extends ICollection <E>{
    boolean add(E element);
    E poll();
    E peek();
}
