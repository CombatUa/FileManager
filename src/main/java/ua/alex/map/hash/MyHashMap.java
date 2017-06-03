package ua.alex.map.hash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MyHashMap {

    private int capacity;
    MapNode[] nodes;

    public MyHashMap() {
        this(4);
    }


    public MyHashMap(int capacity) {
        this.capacity = capacity;
        init();
    }

    private void init() {
        nodes = new MapNode[capacity];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new MapNode();
        }
    }

    private MapNode getNode(int hash) {
        return nodes[Integer.remainderUnsigned(hash,
                capacity)];
    }

    public int size() {
        int tmpSize = 0;
        for (int i = 0; i < nodes.length; i++) {
            tmpSize += nodes[i].size;
        }
        return tmpSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(Object key) {
        return key == null ? null : getNode(key.hashCode()).isExistsKey(key);
    }

    public boolean containsValue(Object value) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isExistsValue(value)) {
                return true;
            }
        }
        return false;
    }


    public Object get(Object key) {

        if (key == null)
            return null;
        return getNode(key.hashCode()).get(key);
    }


    public Object put(Object key, Object value) {

        return key == null ? null : getNode(key.hashCode()).put(key, value) ? key : null;
    }


    public Object putIfAbsent(Object key, Object value) {
        if (containsKey(key)) return null;
        return put(key, value);
    }


    public void putAll(MyHashMap map) {
        for (int i = 0; i < map.nodes.length; i++) {

            map.nodes[i].mapEntitys.forEach(s -> this.put(s.getKey(), s.getValue()));

        }
    }

    public void putAllIfAbsent(MyHashMap map) {
        for (int i = 0; i < map.nodes.length; i++) {
            map.nodes[i].mapEntitys.forEach(s -> this.putIfAbsent(s.getKey(), s.getValue()));
        }

    }

    public Object remove(Object key) {
        if (key == null)
            return null;
        if (getNode(key.hashCode()).remove(key)) {
            return key;
        }

        return null;
    }


    public void clear() {
        init();
    }


    /**
     * to support Node operations
     */
    static class MapNode {

        public int getSize() {
            return size;
        }

        int size = 0;

        List<MapEntity> mapEntitys = new ArrayList<>();
        List val = new ArrayList();

        public boolean put(Object key, Object val) {
            if (isExistsKey(key)) {
                remove(key);
            }
            size++;
            return (this.mapEntitys.add(new MapEntity(key, val)));
        }

        public boolean isExistsKey(Object key) {

            Iterator i = mapEntitys.iterator();
            while (i.hasNext()) {
                if (((MapEntity) i.next()).getKey().equals(key)) {
                    return true;
                }
            }
            return false;

        }

        public boolean isExistsValue(Object value) {

            Iterator i = mapEntitys.iterator();
            while (i.hasNext()) {
                if (((MapEntity) i.next()).getValue().equals(value)) {
                    return true;
                }
            }
            return false;

        }

        public Object get(Object key) {

            Iterator i = mapEntitys.iterator();
            MapEntity tmpMapEntity = null;
            while (i.hasNext()) {
                tmpMapEntity = (MapEntity) i.next();
                if (tmpMapEntity.getKey().equals(key)) {
                    break;
                }
            }
            return tmpMapEntity == null ? null : tmpMapEntity.getValue();

        }

        public boolean remove(Object key) {

            Iterator i = mapEntitys.iterator();
            while (i.hasNext()) {
                if (((MapEntity) i.next()).getKey().equals(key)) {
                    i.remove();
                    size--;
                    return true;
                }
            }
            return false;

        }


    }

    /**
     * to store Map Entity key value pairs
     */
    static class MapEntity {
        Object key;
        Object value;

        public MapEntity(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

    }
}
