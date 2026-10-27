package dev.masterclass.datastructures.hashtable.v2;

import java.util.Objects;

public class ChainedHashTable<K, V> {

    static void main() {
        ChainedHashTable<String, String> table = new ChainedHashTable<>(8);
        table.put("Aa", "Alice");
        table.put("BB", "Bob");
        table.put("Aa", "Anna");
    }

    private static class Entry<K, V> {

        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Entry<K, V>[] buckets;
    private int size;

    // time O(1), space O(initialCapacity)
    public ChainedHashTable(int initialCapacity) {
        buckets = new Entry[roundUpToPowerOfTwo(initialCapacity)];
    }

    // time O(1), space O(1)
    public void put(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        int index = getIndex(key);

        Entry<K, V> current = buckets[index];

        // Update existing key
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        // Insert new entry at HEAD
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;

        size++;
    }

    // time O(1), space O(1)
    public V get(K key) {
        Objects.requireNonNull(key);

        int index = getIndex(key);

        Entry<K, V> current = buckets[index];

        while (current != null) {

            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    // time O(1), space O(1)
    public boolean remove(K key) {
        Objects.requireNonNull(key);

        int index = getIndex(key);

        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = null;

        while (current != null) {

            if (current.key.equals(key)) {

                if (prev == null) {
                    // Remove HEAD
                    buckets[index] = current.next;
                } else {
                    // Remove MIDDLE or TAIL
                    prev.next = current.next;
                }

                size--;
                return true;
            }

            prev = current;
            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    // time O(1), space O(1)
    private int getIndex(K key) {
        int hash = key.hashCode();
        hash ^= (hash >>> 16);

        return (buckets.length - 1) & hash;
    }

    private int roundUpToPowerOfTwo(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Invalid capacity");
        }
        if (n >= (1 << 30)) {
            throw new IllegalArgumentException("Overflow");
        }

        return 1 << (32 - Integer.numberOfLeadingZeros(n - 1));
    }

}
