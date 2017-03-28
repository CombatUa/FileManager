package ua.alex.map.hash;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Manny on 3/27/17.
 */
class MyHashMapTest {

    @Test
    void size() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "val1");
        assertEquals(1, myHashMap.size());
    }

    @Test
    void containsKey() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "val1");
        myHashMap.put("key2", "val2");
        assertTrue(myHashMap.containsKey("key2"));
    }

    @Test
    void not_containsKey() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "val1");
        myHashMap.put("key2", "val2");
        myHashMap.put("key3", "val3");
        assertFalse(myHashMap.containsKey("key8"));
    }

    @Test
    void containsValue() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "val1");
        myHashMap.put("key2", "val2");
        myHashMap.put("key3", "val3");
        assertTrue(myHashMap.containsValue("val2"));
    }

    @Test
    void get() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "val1");
        myHashMap.put("key2", "val2");
        myHashMap.put("key3", "val3");
        assertEquals("val2", myHashMap.get("key2"));
    }

    @Test
    void isEmpty() {
        MyHashMap myHashMap = new MyHashMap();
        assertTrue(myHashMap.isEmpty());
    }

    @Test
    void put() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "val1");
        assertTrue(myHashMap.containsKey("key1"));
        assertTrue(myHashMap.containsValue("val1"));
        myHashMap.put("key1", "val2");
        assertEquals("val2", myHashMap.get("key1"));

    }

    @Test
    void remove() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "val1");
        myHashMap.put("key2", "val2");
        myHashMap.put("key3", "val3");
        myHashMap.remove("key3");
        assertFalse(myHashMap.containsKey("key3"));
    }

    @Test
    void clear() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "val1");
        myHashMap.put("key2", "val2");
        myHashMap.put("key3", "val3");
        myHashMap.clear();
        assertTrue(myHashMap.isEmpty());

    }

    @Test
    void putAll() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "val1");
        myHashMap.put("key2", "val2");
        myHashMap.put("key3", "val3");
        MyHashMap myHashMap2 = new MyHashMap();
        myHashMap2.put("key1", "val1_1");
        myHashMap2.put("key2", "val2_2");
        myHashMap2.put("key4", "val4");
        myHashMap.putAll(myHashMap2);
        assertEquals("val1_1", myHashMap.get("key1"));
        assertEquals(4, myHashMap.size());
    }

    @Test
    void putIputIfAbsent() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "val1");
        myHashMap.put("key2", "val2");
        myHashMap.put("key3", "val3");
        myHashMap.putIfAbsent("key1", "val4");
        myHashMap.putIfAbsent("key4", "val4");
        assertEquals("val1", myHashMap.get("key1"));
        assertEquals("val4", myHashMap.get("key4"));


    }

    @Test
    void putAllIfAbsent() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "val1");
        myHashMap.put("key2", "val2");
        myHashMap.put("key3", "val3");
        MyHashMap myHashMap2 = new MyHashMap();
        myHashMap2.put("key1", "val1_1");
        myHashMap2.put("key2", "val2_2");
        myHashMap2.put("key4", "val4");
        myHashMap.putAllIfAbsent(myHashMap2);
        assertEquals("val1", myHashMap.get("key1"));
        assertEquals("val4", myHashMap.get("key4"));


    }
}