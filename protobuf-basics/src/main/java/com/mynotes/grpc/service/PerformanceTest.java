package com.mynotes.grpc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mynotes.models.basic.Person;
import com.mynotes.grpc.service.json.PersonJson;

public class PerformanceTest {

    public static void main(String[] args) {

        //json
        Runnable jsonRunnable = () -> {
            PersonJson personJson = new PersonJson();
            personJson.setFname("John");
            personJson.setAge(25);
            ObjectMapper mapper = new ObjectMapper();
            try {
                byte[] bytes = mapper.writeValueAsBytes(personJson);
                PersonJson personJsonResult = mapper.readValue(bytes, PersonJson.class);
                personJsonResult.getAge();
            } catch (Exception e) {
                e.printStackTrace();
            }

        };

        //protobuf
        Runnable protobufRunnable = () -> {
            Person person = Person.newBuilder().setFName("John").setAge(25).build();
            try {
                byte[] bytes = person.toByteArray();
                Person personResult = Person.parseFrom(bytes);
                personResult.getAge();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            System.out.println("===Iteration " + i + "===");
            test(jsonRunnable, "JSON");
            test(protobufRunnable, "PROTOBUF");
        }

    }

    static void test(Runnable runnable, String testType) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            runnable.run();
        }
        long stop = System.currentTimeMillis();

        System.out.println(testType + " :: " + (stop - start) + "ms");

    }
}
