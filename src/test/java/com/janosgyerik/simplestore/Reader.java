package com.janosgyerik.simplestore;

import java.io.IOException;
import java.io.InputStream;

interface Reader<V> {
    V parseFrom(InputStream in) throws IOException;
}
