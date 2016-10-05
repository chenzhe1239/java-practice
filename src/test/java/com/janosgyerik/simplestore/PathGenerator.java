package com.janosgyerik.simplestore;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PathGenerator implements Iterator<Path> {

    private final AtomicInteger counter = new AtomicInteger(0);

    private final int levels;
    private final int filesPerLevel;
    private final Path base;

    private final int limit;

    public PathGenerator(int levels, int filesPerLevel, Path base) {
        if (levels < 1) {
            throw new IllegalArgumentException("levels must be > 0");
        }
        if (filesPerLevel < 1) {
            throw new IllegalArgumentException("filesPerLevel must be > 0");
        }

        this.levels = levels;
        this.filesPerLevel = filesPerLevel;
        this.base = base;

        limit = (int) Math.pow(filesPerLevel, levels);
    }

    @Override
    public boolean hasNext() {
        return counter.get() < limit;
    }

    @Override
    public Path next() {
        if (!hasNext()) {
            throw new NoSuchElementException("path generator exhausted");
        }

        int count = counter.getAndIncrement();

        List<String> parts = new ArrayList<>(levels);
        for (int i = 0; i < levels; i++) {
            parts.add(Integer.toString(count % filesPerLevel));
            count /= filesPerLevel;
        }

        Collections.reverse(parts);

        Path path = base;
        for (String part : parts) {
            path = path.resolve(part);
        }

        return path;
    }
}
