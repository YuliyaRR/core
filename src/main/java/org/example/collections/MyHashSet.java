package org.example.collections;

import java.util.Iterator;

public class MyHashSet<E> implements ISet<E> {
    private IMap<E, Object> map;
    private static final Object OBJECT = new Object();

    public MyHashSet() {
        this.map = new MyHashMap<>();
    }

    @Override
    public boolean add(E element) {
        if(this.map.get(element) == null) {
            this.map.put(element, OBJECT);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(E element) {
        return this.map.remove(element);
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public boolean contains(E element) {
        return this.map.get(element) != null;
    }

    @Override
    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }
}
