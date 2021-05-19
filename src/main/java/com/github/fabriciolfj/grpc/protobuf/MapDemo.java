package com.github.fabriciolfj.grpc.protobuf;

import com.github.fabriciolfj.protobuf.Car;
import com.github.fabriciolfj.protobuf.Dealer;

public class MapDemo {

    public static void main(String[] args) {
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

        Dealer dealer =  Dealer.newBuilder()
                .putModel(2005, honda)
                .putModel(2020, civic)
                .build();

        System.out.println(
                dealer.getModelMap()
        );
    }
}
