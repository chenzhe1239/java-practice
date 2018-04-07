#!/usr/bin/env bash

class=$1
class=${class#src/test/java/}
class=${class%.java}
class=${class//\//.}

java -cp target/test-classes $class
