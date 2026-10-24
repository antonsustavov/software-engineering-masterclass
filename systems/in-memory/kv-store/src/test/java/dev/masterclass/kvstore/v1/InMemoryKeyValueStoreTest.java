package dev.masterclass.kvstore.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryKeyValueStoreTest {

    private KeyValueStore store;

    @BeforeEach
    void setUp() {
        store = new InMemoryKeyValueStore();
    }

    @Test
    void shouldPutAndGetValue() {
        store.put("user:1", "Alice");

        assertEquals(1, store.size());
        assertEquals(Optional.of("Alice"), store.get("user:1"));
    }

    @Test
    void shouldPreserveStateAfterInvalidPut() {
        assertEquals(0, store.size());

        store.put("user:1", "Alice");
        assertEquals(1, store.size());

        assertThrows(
            NullPointerException.class,
            () -> store.put(null, "Alice")
        );

        assertThrows(
            NullPointerException.class,
            () -> store.put("user:1", null)
        );

        assertEquals(1, store.size());
        assertEquals(Optional.of("Alice"), store.get("user:1"));
    }

    @Test
    void shouldCheckContainsKey() {
        assertFalse(store.containsKey("user:1"));
        assertEquals(0, store.size());

        store.put("user:1", "Alice");

        assertTrue(store.containsKey("user:1"));
        assertEquals(1, store.size());

        store.delete("user:1");

        assertFalse(store.containsKey("user:1"));
        assertEquals(0, store.size());
    }

    @Test
    void shouldOverwriteExistingValue() {
        store.put("user:1", "Alice");

        assertEquals(1, store.size());
        assertEquals(Optional.of("Alice"), store.get("user:1"));

        store.put("user:1", "Bob");

        assertEquals(1, store.size());
        assertEquals(Optional.of("Bob"), store.get("user:1"));
    }

    @Test
    void shouldDeleteExistingKey() {
        store.put("user:1", "Alice");

        assertEquals(1, store.size());
        assertEquals(Optional.of("Alice"), store.get("user:1"));
        assertTrue(store.delete("user:1"));

        assertEquals(0, store.size());
        assertEquals(Optional.empty(), store.get("user:1"));
        assertFalse(store.delete("user:1"));
    }

    @Test
    void shouldReturnEmptyForMissingKey() {
        store.put("user:1", "Alice");

        assertEquals(1, store.size());
        assertEquals(Optional.of("Alice"), store.get("user:1"));

        assertEquals(Optional.empty(), store.get("user:2"));
    }

    @Test
    void shouldRejectNullArguments() {

        assertThrows(NullPointerException.class, () -> store.put(null, "Alice"));

        assertThrows(NullPointerException.class, () -> store.put("user:1", null));

        assertThrows(NullPointerException.class, () -> store.get(null));

        assertThrows(NullPointerException.class, () -> store.delete(null));

        assertThrows(NullPointerException.class, () -> store.containsKey(null));
    }

}
