package org.example.functional_programming.custom_collectors;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyGroupingCollector <T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {
    private final Function<T, K> grouping;

    public MyGroupingCollector(Function<T, K> grouping) {
        this.grouping = grouping;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (map, element) -> {
            K key = grouping.apply(element);
            List<T> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(element);
            map.put(key, list);
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return (map1, map2) -> {
            map1.forEach((key, value) -> {
                if(map2.containsKey(key)) {
                    List<T> valuesMap2 = map2.get(key);
                    valuesMap2.addAll(value);
                } else {
                    map2.put(key, value);
                }
            });
            return map2;
        };
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
