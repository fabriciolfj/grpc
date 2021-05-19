package com.github.fabriciolfj.grpc.protobuf;

import com.github.fabriciolfj.protobuf.Address;
import com.github.fabriciolfj.protobuf.Car;
import com.github.fabriciolfj.protobuf.Person;

import java.util.Arrays;
import java.util.List;

public class CompositionDemo {

    public static void main(String[] args) {
        Address address = Address.newBuilder()
                .setCity("Serrana")
                .setStreet("Rua acre")
                .build();

        Car honda  = Car.newBuilder()
                .setMake("Honda")
                .setModel("Accord")
                .setYear(2020)
                .build();

        Car civic  = Car.newBuilder()
                .setMake("Honda")
                .setModel("Civic")
                .setYear(2020)
                .build();

        List<Car> cars = Arrays.asList(civic, honda);

        Person person = Person.newBuilder()
                .setName("Fabricio")
                .setAge(36)
                .setAddress(address)
                .addAllCars(cars)
                .build();

        System.out.println(person.toString());
    }
}
