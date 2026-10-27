package dev.masterclass.datastructures.hashtable.v2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChainedHashTableTest {

    private ChainedHashTable<String, String> table;

    @BeforeEach
    void setUp() {
        table = new ChainedHashTable<>(8);
    }

    @Test
    void shouldHandleHashCollisions() {
        table.put("Aa", "Alice");
        table.put("BB", "Bob");
        table.put("Aa", "Anna");
        String value = table.get("Aa");

        assertEquals("Anna", value);
    }

    @Test
    void shouldUpdateExistingKeyWithoutIncreasingSize() {
        table.put("Aa", "Alice");
        table.put("BB", "Bob");
        table.put("Aa", "Anna");

        assertEquals(2, table.size());
    }

    @Test
    void shouldRemoveHeadMiddleAndTail() {
        table.put("Aa", "Alice");
        table.put("Bb", "Bob");
        table.put("BB", "Bob");
        table.put("Aa", "Anna");

        table.remove("Aa");
        assertNull(table.get("Aa"));
        assertEquals(2, table.size());
        assertEquals("Bob", table.get("BB"));
        assertEquals("Bob", table.get("Bb"));

        table.remove("BB");
        assertNull(table.get("BB"));
        assertEquals(1, table.size());
        assertEquals("Bob", table.get("Bb"));

        table.remove("Aa");
        assertNull(table.get("Aa"));
        assertEquals(1, table.size());
        assertEquals("Bob", table.get("Bb"));

        assertFalse(table.remove("Aa"));
        assertFalse(table.remove("BB"));
    }

    @Test
    void shouldCheckMissingKey() {
        table.put("Aa", "Alice");
        table.put("Bb", "Bob");
        table.put("BB", "Bob");
        table.put("Aa", "Anna");

        assertNull(table.get("AA"));
    }

    @Test
    void shouldReturnNullForMissingKey() {
        table.put("Aa", "Alice");

        assertNull(table.get("BB"));
        assertEquals("Alice", table.get("Aa"));
    }

    @Test
    void shouldRemoveMiddleNode() {
        table.put("Aa", "Alice");
        table.put("Bb", "Bob");
        table.put("BB", "Bob");
        table.put("Aa", "Anna");

        assertTrue(table.remove("Bb"));
        assertNull(table.get("Bb"));

        assertEquals("Anna", table.get("Aa"));
        assertEquals("Bob", table.get("BB"));
        assertEquals(2, table.size());
    }

}
