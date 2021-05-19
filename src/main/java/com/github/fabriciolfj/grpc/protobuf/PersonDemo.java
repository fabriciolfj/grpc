package com.github.fabriciolfj.grpc.protobuf;

import com.github.fabriciolfj.protobuf.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonDemo {

    public static void main(String[] args) throws IOException {
        final Person person = Person.newBuilder()
                .setAge(36)
                .setName("Fabricio")
                .build();

        final Path path = Paths.get("sam.ser");
        Files.write(path, person.toByteArray());

        byte[] bytes = Files.readAllBytes(path);
        final Person newPerson = Person.parseFrom(bytes);

        System.out.println(newPerson.toString());
    }
}
