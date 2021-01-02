package com.mynotes.protobuf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mynotes.models.basic.Person;
import com.mynotes.protobuf.json.PersonJson;

public class PersonTest {

    public static void main(String[] args) {
        //json

        PersonJson personJson = new PersonJson();
        personJson.setFname("John");
        personJson.setAge(25);
        ObjectMapper mapper = new ObjectMapper();
        try {
            byte[] bytes = mapper.writeValueAsBytes(personJson);
            System.out.println("Json=" + bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //protobuf
        Person person = Person.newBuilder().setFName("John").setAge(25).build();
        try {
            byte[] bytes = person.toByteArray();
            System.out.println("ProtoBuf=" + bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
