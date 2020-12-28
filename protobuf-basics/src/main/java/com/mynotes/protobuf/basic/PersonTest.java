package com.mynotes.protobuf.basic;

import com.mynotes.models.basic.Person;

public class PersonTest {

    public static void main(String[] args) {

        Person person = Person.newBuilder().setFName("John").setAge(25).build();

        System.out.println(person.toString());

    }

}
