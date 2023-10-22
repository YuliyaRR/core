package org.example.functional_programming.custom_collectors;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyMapCollector<T, K, V> implements Collector<T, Map<K, V>, Map<K, V>> {
    private final Function<T, K> keyFunction;
    private final Function<T, V> valueFunction;

    public MyMapCollector(Function<T, K> keyFunction, Function<T, V> valueFunction) {
        this.keyFunction = keyFunction;
        this.valueFunction = valueFunction;
    }

    @Override
    public Supplier<Map<K, V>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, V>, T> accumulator() {
        return (map, element) -> {
            K key = keyFunction.apply(element);
            V value = valueFunction.apply(element);
            map.put(key, value);
        };
    }

    @Override
    public BinaryOperator<Map<K, V>> combiner() {
        return (map1, map2) -> {
            for (Map.Entry<K, V> entry : map1.entrySet()) {
                K key = entry.getKey();
                V value = entry.getValue();
                map2.putIfAbsent(key, value);
            }
            return map2;
        };
    }

    @Override
    public Function<Map<K, V>, Map<K, V>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
