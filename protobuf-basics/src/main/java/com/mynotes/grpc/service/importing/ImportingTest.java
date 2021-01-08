package com.mynotes.grpc.service.importing;

import com.mynotes.importing.commons.Date;
import com.mynotes.importing.models.Person;

public class ImportingTest {

    public static void main(String[] args) {

        Date birthday = Date.newBuilder().setDay(3).setMonth(02).setYear(1987).build();

        Person person = Person.newBuilder()
            .setFName("John")
            .setLName("Doe")
            .setAge(25)
            .setBirthday(birthday).build();

        System.out.println(person);

    }

}
