package com.mynotes.protobuf;

import com.mynotes.models.Person;

public class PersonTest {

    public static void main(String[] args) {

        Person person = Person.newBuilder().setFName("John").setAge(25).build();

        System.out.println(person.toString());

    }

}
