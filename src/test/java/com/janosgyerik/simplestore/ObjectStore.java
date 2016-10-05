package com.janosgyerik.simplestore;

import java.io.IOException;
import java.util.Optional;

public interface ObjectStore<K, V> {

    void write(K key, V value) throws IOException;

    Optional<V> read(K key) throws IOException;
}
