package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    //8.2. Реализуй в классе следующие вспомогательные методы:
    public int hash(Long k){
        k ^= (k >>> 20) ^ (k >>> 12);
        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }
    public int indexFor(int hash, int length){
        return hash & (length - 1);
    }
    public Entry getEntry(Long key){}

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

    public void addEntry(int hash, Long key, String value, int bucketIndex){}
    public void createEntry(int hash, Long key, String value, int bucketIndex){}

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

    }

    @Override
    public Long getKey(String value) {
        return null;
    }

    @Override
    public String getValue(Long key) {
        return null;
    }
}
