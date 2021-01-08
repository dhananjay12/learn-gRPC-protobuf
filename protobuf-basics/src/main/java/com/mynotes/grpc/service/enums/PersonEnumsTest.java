package com.mynotes.grpc.service.enums;

import com.mynotes.models.enums.Person;
import com.mynotes.models.enums.Person.EyeColour;

public class PersonEnumsTest {

    public static void main(String[] args) {


        Person person = Person.newBuilder().setFName("John").setLName("Doe")
            .setAge(25).setEyeColour(EyeColour.BLUE).build();

        System.out.println(person.toString());

    }

}
