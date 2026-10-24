package dev.masterclass.kvstore.v1;

import java.util.Optional;

public interface KeyValueStore {

    void put(String key, String value);

    Optional<String> get(String key);

    boolean delete(String key);

    boolean containsKey(String key);

    int size();
}
