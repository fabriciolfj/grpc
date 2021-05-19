package com.github.fabriciolfj.grpc.protobuf;

import com.github.fabriciolfj.protobuf.*;
import com.google.protobuf.Int32Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonDemo {

    public static void main(String[] args) throws IOException {
        final Person person = Person.newBuilder()
                .setAge(Int32Value.newBuilder().setValue(36).build())
                .setName("Fabricio")
                .build();

        final Path path = Paths.get("sam.ser");
        Files.write(path, person.toByteArray());

        byte[] bytes = Files.readAllBytes(path);
        final Person newPerson = Person.parseFrom(bytes);

        System.out.println(newPerson.toString());
        System.out.println(person.hasAge());
    }
}
