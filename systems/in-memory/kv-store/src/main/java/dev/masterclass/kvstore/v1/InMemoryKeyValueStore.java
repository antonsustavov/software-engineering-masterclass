package dev.masterclass.kvstore.v1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Design and implement a simple in-memory key-value store that supports storing, retrieving, updating, and deleting
 * string values by string keys. The store must provide constant-time average lookup and update operations. Initially,
 * it is used by a single thread and does not require persistence.
 */

//Step 2 — Clarifying Questions

//Can keys or values be null?  Контракт и валидация! String, String
//Should put overwrite existing values?	Семантика обновления!  Перезапись
//What should get return for a missing key?	API contract! Optional.empty()
//Do we need to return the previous value?	Семантика put/remove
//Is the store accessed concurrently?	Thread safety! Не сейчас
//Must data survive process restarts?	Persistence! Не требуется
//Do we need expiration or eviction?	Memory management! Не требуется

public class InMemoryKeyValueStore implements KeyValueStore {

    private final Map<String, String> store = new HashMap<>();

    @Override
    public void put(String key, String value) {
        Objects.requireNonNull(key, "Key cannot be null");
        Objects.requireNonNull(value, "Value cannot be null");
        store.put(key, value);
    }

    @Override
    public Optional<String> get(String key) {
        Objects.requireNonNull(key, "Key cannot be null");
        return Optional.ofNullable(store.get(key));
    }

    @Override
    public boolean delete(String key) {
        Objects.requireNonNull(key, "Key cannot be null");
        return store.remove(key) != null;
    }

    @Override
    public boolean containsKey(String key) {
        Objects.requireNonNull(key, "Key cannot be null");
        return store.containsKey(key);
    }

    @Override
    public int size() {
        return store.size();
    }
}
