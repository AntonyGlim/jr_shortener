package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    public int hash(Long k){
        k ^= (k >>> 20) ^ (k >>> 12);
        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }

    public int indexFor(int hash, int length){
        return hash & (length - 1);
    }

    public Entry getEntry(Long key){
        int hash = (key != null) ? hash(key) : 0;
        for (Entry e = table[indexFor(hash, table.length)];
             e != null;
             e = e.getNext()) {
            Long k;
            if (e.getHash() == hash &&
                    ((k = e.getKey()) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }

    public void resize(int newCapacity){
        Entry[] oldTable = table;
        int oldCapasity = oldTable.length;
        if(oldCapasity == 1 << 30){
            threshold = Integer.MAX_VALUE;
            return;
        }
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    public void transfer(Entry[] newTable){
        for (int i = 0; i < table.length; i++) {
            newTable[i] = table[i];
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        return false;
    }

    @Override
    public void put(Long key, String value) {
        addEntry(hash(key), key, value, indexFor(hash(key), table.length));
    }

    @Override
    public Long getKey(String value) {
        for (Entry entry : table) {
            if (entry.getValue().equals(value)){
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        for (int i = 0; i < table.length; i++) {
            Entry entry = table[i];
            if (key.equals(entry.getKey())){
                return entry.getValue();
            }
        }
        return null;
    }
}
