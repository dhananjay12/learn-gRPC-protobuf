package com.mynotes.protobuf.composition;

import com.mynotes.models.composition.Person;
import com.mynotes.models.composition.Date;

public class PersonCompositionTest {

    public static void main(String[] args) {

        Date birthday = Date.newBuilder().setDay(3).setMonth(02).setYear(1987).build();

        Person person = Person.newBuilder().setFName("John").setLName("Doe")
            .setAge(25).setBirthday(birthday).build();

        System.out.println(person.toString());

    }

}
