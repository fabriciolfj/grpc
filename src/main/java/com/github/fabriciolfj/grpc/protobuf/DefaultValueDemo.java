package com.github.fabriciolfj.grpc.protobuf;

import com.github.fabriciolfj.protobuf.Person;
import com.google.protobuf.Int32Value;

public class DefaultValueDemo {

    public static void main(String[] args) {
        Person person = Person.newBuilder()
                .setAge(Int32Value.newBuilder().setValue(36).build())
                .setName("Fabricio")
                .build();

        System.out.println(
                "City: " + person.getAddress().getCity()
        );

        System.out.println(
                person.hasAddress() //valor default
        );
    }
}
