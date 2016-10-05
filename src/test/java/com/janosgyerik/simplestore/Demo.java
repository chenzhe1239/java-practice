package com.janosgyerik.simplestore;

import com.example.tutorial.AddressBookProtos;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.Arrays;

public class Demo {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test_1() throws IOException {
        PathGenerator pathGenerator = new PathGenerator(2, 2, folder.getRoot().toPath());
        Reader<AddressBookProtos.Person> reader = AddressBookProtos.Person::parseFrom;
        Writer<AddressBookProtos.Person> writer = (out, value) -> value.writeTo(out);
        ObjectStore<String, AddressBookProtos.Person> store = new SimpleObjectStore<>(pathGenerator, reader, writer);

        AddressBookProtos.Person.Builder builder = AddressBookProtos.Person.newBuilder();
        AddressBookProtos.Person jack = builder.setName("Jack").setId(1).build();
        AddressBookProtos.Person mike = builder.setName("Mike").setId(2).build();

        store.write(jack.getName(), jack);
        store.write(mike.getName(), mike);
        System.out.println(Arrays.toString(folder.getRoot().listFiles()));

        System.out.println(store.read(jack.getName()));
        System.out.println(store.read(mike.getName()));
    }
}
