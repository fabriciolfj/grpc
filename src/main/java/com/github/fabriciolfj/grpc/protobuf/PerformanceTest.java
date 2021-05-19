package com.github.fabriciolfj.grpc.protobuf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.grpc.json.JPerson;
import com.github.fabriciolfj.protobuf.Person;
import com.google.protobuf.InvalidProtocolBufferException;

public class PerformanceTest {

    public static void main(String[] args) {
        //json
        final JPerson jPerson = new JPerson("fabricio", 36);
        final ObjectMapper mapper = new ObjectMapper();


        Runnable runnable1 = () -> {
            try {
                byte[] bytes = mapper.writeValueAsBytes(jPerson);
                JPerson newJperson = mapper.readValue(bytes, JPerson.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        //protobuf
        final Person person = Person.newBuilder()
                .setAge(36)
                .setName("Fabricio")
                .build();

        Runnable runnable2 = () -> {
            try {
                byte[] bytes = person.toByteArray();
                Person newPerson = Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            runPerformanceTest(runnable1, "json");
            runPerformanceTest(runnable2, "proto");
        }
    }

    private static void runPerformanceTest(Runnable runnable, String method) {
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            runnable.run();
        }
        long time2 = System.currentTimeMillis();

        System.out.println(
                method + " : " + (time2 - time1)+ "ms");
    }
}
