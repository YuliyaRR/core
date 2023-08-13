package org.example.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashMapTest {
    @Test
    public void put100DifferentElementsThenSizeBecome100(){
        IMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put("Element" + i, i);
        }
        assertEquals(100, map.size());
    }

    @Test
    public void put100ElementsWithDuplicatesThenSizeBecome10(){
        IMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put("Element" + i%10, i);
        }
        assertEquals(10, map.size());
    }

    @Test
    public void putElementsWithTheSameHashCodeCheckRecursionFirstLoop(){
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(19, "put19");
        assertEquals(2, map.size());
    }

    @Test
    public void putElementsWithTheSameHashCodeCheckRecursionSecondLoop(){
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(19, "put19");
        map.put(35, "put35");
        assertEquals(3, map.size());
    }

    @Test
    public void putElementsWithTheSameKeyAndDifferentValue(){
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(3, "put5");
        assertEquals(1, map.size());
        assertEquals("put5", map.get(3));
    }

    @Test
    public void removeExistentElementByKey(){
        IMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("Element" + i, i);
        }
        assertTrue(map.remove("Element3"));
        assertEquals(9, map.size());
    }

    @Test
    public void removeNonexistentElementByKey(){
        IMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("Element" + i, i);
        }
        assertFalse(map.remove("Element13"));
        assertEquals(10, map.size());
    }

    @Test
    public void removeExistentElementByKeyTwiceFalseInResult(){
        IMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("Element" + i, i);
        }
        map.remove("Element3");
        assertFalse(map.remove("Element3"));
        assertEquals(9, map.size());
    }

    @Test
    public void removeElementFromCellWhenNextIsNotNull() {
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(19, "put19");
        map.put(35, "put35");
        assertTrue(map.remove(3));
        assertEquals(2, map.size());
    }

    @Test
    public void removeElementFromInternalLinkedListWhenNextIsNotNull() {
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(19, "put19");
        map.put(35, "put35");
        assertTrue(map.remove(19));
        assertEquals(2, map.size());
    }

    @Test
    public void removeNonexistentElementFromInternalLinkedListWhenNextIsNotNull() {
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(19, "put19");
        map.put(35, "put35");
        assertFalse(map.remove(67));
        assertEquals(3, map.size());
    }

    @Test
    public void getExistentElementByKey() {
        IMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("Element" + i, i);
        }
        assertEquals(5, map.get("Element5"));
    }

    @Test
    public void getElementWithTheSameHashCodeOfKey(){
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(19, "put19");
        map.put(35, "put35");
        assertEquals("put35", map.get(35));
    }

    @Test
    public void getNonexistentElementByKey() {
        IMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("Element" + i, i);
        }
        assertNull(map.get("Element2225"));
    }

    @Test
    public void getNonexistentElementWithTheSameHashCodeOfKey(){
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(19, "put19");
        assertNull(map.get(35));
    }

    @Test
    public void getKeySetEqualsInResult(){
        IMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("Element" + i, i);
        }
        Set<String> keySetExpected = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            keySetExpected.add("Element" + i);
        }
        assertEquals(keySetExpected, map.keySet());
    }

    @Test
    public void getKeySetWithElementsFromInternalLinkedList(){
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(19, "put19");
        map.put(35, "put35");
        map.put(13, "put13");
        map.put(1, "put1");
        Set<Integer>keySetExpected = new HashSet<>(List.of(3,19,35,13,1));

        assertEquals(keySetExpected, map.keySet());
    }

    @Test
    public void getKeySetNotEqualsInResult(){
        IMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("Element" + i, i);
        }
        Set<String> keySetExpected = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            keySetExpected.add("Element" + (i-100));
        }
        assertNotEquals(keySetExpected, map.keySet());
    }

    @Test
    public void getAllValues(){
        IMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("Element" + i, i);
        }

        List<Integer>listValuesExpected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listValuesExpected.add(i);
        }
        assertEquals(listValuesExpected, map.values());
    }

    @Test
    public void getAllValuesWithElementsFromInternalLinkedList(){
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(19, "put19");
        map.put(35, "put35");
        map.put(13, "put13");
        map.put(1, "put1");

        List<String>listValuesExpected = new ArrayList<>(List.of("put3","put19","put35","put13","put1"));

        assertEquals(listValuesExpected.size(), map.values().size());
    }

    @Test
    public void getAllValuesWithDuplicatesValuesOfElementsFromInternalLinkedList(){
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(19, "put19");
        map.put(35, "put3");
        map.put(13, "put1");
        map.put(1, "put1");

        List<String>listValuesExpected = new ArrayList<>(List.of("put3","put19","put3","put1","put1"));
        assertEquals(listValuesExpected.size(), map.values().size());
    }

    @Test
    public void getAllValuesWhenSomeValuesAreNull(){
        IMap<Integer, String> map = new MyHashMap<>();
        map.put(3, "put3");
        map.put(19, null);
        map.put(35, "put35");
        map.put(13, "put13");
        map.put(1, null);

        List<String>listValuesExpected = new ArrayList<>();
        listValuesExpected.add("put3");
        listValuesExpected.add(null);
        listValuesExpected.add("put35");
        listValuesExpected.add("put13");
        listValuesExpected.add(null);
        assertEquals(listValuesExpected.size(), map.values().size());
    }

    @Test
    public void clear(){
        IMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("Element" + i, i);
        }
        assertEquals(10, map.size());
        map.clear();
        assertEquals(0, map.size());
    }
}
