package com.janosgyerik.simplestore;

import java.io.IOException;
import java.io.OutputStream;

interface Writer<V> {
    void writeTo(OutputStream out, V value) throws IOException;
}
