package dev.masterclass.datastructures.hashtable.v1;

import java.util.Objects;

public class SimpleHashTable<K, V> {

    private final Object[] buckets;

    // time O(1), space O(initialCapacity)
    public SimpleHashTable(int initialCapacity) {
        buckets = new Object[initialCapacity];
    }

    // time O(1), space O(initialCapacity)
    public void put(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        int index = getIndex(key);
        buckets[index] = value;
    }

    // time O(1), space O(initialCapacity)
    public V get(K key) {
        Objects.requireNonNull(key);

        int index = getIndex(key);

        return (V) buckets[index];
    }

    // time O(1), space O(initialCapacity)
    public boolean remove(K key) {
        Objects.requireNonNull(key);

        int index = getIndex(key);
        if (buckets[index] != null) {
            buckets[index] = null;

            return true;
        }

        return false;
    }

    // time O(1), space O(initialCapacity)
    private int getIndex(K key) {
        int hash = Objects.hash(key);

        return (buckets.length - 1) & hash;
    }

}
