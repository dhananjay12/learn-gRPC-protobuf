package com.mynotes.protobuf.defaults;

import com.mynotes.models.defaults.Person;

public class DefaultTest {

    public static void main(String[] args) {
        Person person = Person.newBuilder().build();

        System.out.println(person.getAge());
        System.out.println(person.getEyeColour());
        System.out.println(person.getFName().length());
        System.out.println(person.getAddressesList().size());


        //Non scalar types (External modules only) will have has*
        // methods auto generated to check if there is any value or not

        System.out.println(person.hasBirthday());

    }

}
