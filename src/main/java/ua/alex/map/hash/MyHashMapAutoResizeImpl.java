package ua.alex.map.hash;

public class MyHashMapAutoResizeImpl<K, V> {

    private int size = 0;
    private int nodesIncrement;
    private int nodeMaxCapacity;
    private int nodesNumber;
    private int nodesCapacity = NODE_CAPACITY;
    private static final int NODE_CAPACITY = 4;
    private static final int MIN_INITIAL_NODE_NUMBER = 2;
    private MapEntity[][] mapEntities;


    public MyHashMapAutoResizeImpl() {
        this(4);

    }

    public MyHashMapAutoResizeImpl(int capacity) {
        //calculate initial nodeMaxCapacity
        nodesNumber = calculateNodesNumber(capacity);
        mapEntities = new MapEntity[nodesNumber][NODE_CAPACITY];

    }

    private int calculateNodesNumber(int requestedSize) {
        return (requestedSize <= NODE_CAPACITY) ? MIN_INITIAL_NODE_NUMBER : (int) Math.ceil(requestedSize / NODE_CAPACITY);
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size() == 0;
    }


    public boolean containsKey(K key) {
        return false;
    }


    public boolean containsValue(Object value) {
        return false;
    }


    public V get(K key) {
        V v = null;
        return v;
    }


    public V put(K key, V value) {
        int keyHash = key.hashCode();
        int nodeNumber = getNodeByHash(keyHash);
        if (mapEntities[nodeNumber].length > NODE_CAPACITY) {
            increaseNumberOfNodes();
            nodeNumber = getNodeByHash(keyHash);
        }
        ;
        boolean isUpdated = false;
        for (MapEntity mapEntity : mapEntities[nodeNumber]) {
            if (key.equals(mapEntity.key)) {
                mapEntity.val = value;
                isUpdated = true;
                break;
            }
        }
        if (!isUpdated) {
            //    mapEntities[nodeNumber][mapEntities[nodeNumber].length]
        }

        //ArrayList
        return value;
    }

    private void increaseNumberOfNodes() {

    }

    private int getNodeByHash(int hash) {
        return Integer.remainderUnsigned(hash, nodesNumber);
    }


    public V putIfAbsent(K key, V value) {
        return null;
    }

    public void putAll(MyHashMapAutoResizeImpl map) {
    }


    public void putAllIfAbsent(MyHashMapAutoResizeImpl map) {

    }

    public V remove(K key) {
        return null;
    }

    public void clear() {
    }

    private class MapEntity<K, V> {
        K key;
        V val;

        public MapEntity(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
}
