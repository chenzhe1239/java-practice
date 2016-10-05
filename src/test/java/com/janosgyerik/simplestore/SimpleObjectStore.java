package com.janosgyerik.simplestore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleObjectStore<K, V> implements ObjectStore<K, V> {

    private final Map<K, Path> index = new HashMap<>();

    private final PathGenerator pathGenerator;
    private final Reader<V> reader;
    private final Writer<V> writer;

    public SimpleObjectStore(PathGenerator pathGenerator, Reader<V> reader, Writer<V> writer) {
        this.pathGenerator = pathGenerator;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public Optional<V> read(K key) throws IOException {
        Path path = index.get(key);
        if (path == null) {
            return Optional.empty();
        }
        try (InputStream in = Files.newInputStream(path)) {
            return Optional.of(reader.parseFrom(in));
        }
    }

    @Override
    public void write(K key, V value) throws IOException {
        Path path = index.get(key);
        if (path == null) {
            path = pathGenerator.next();
            index.put(key, path);

            File parentFile = path.toFile().getParentFile();
            if (!parentFile.isDirectory()) {
                if (!parentFile.mkdirs()) {
                    throw new IOException("could not create directory: " + parentFile);
                }
            }
        }
        try (OutputStream out = Files.newOutputStream(path)) {
            writer.writeTo(out, value);
        }
    }
}
