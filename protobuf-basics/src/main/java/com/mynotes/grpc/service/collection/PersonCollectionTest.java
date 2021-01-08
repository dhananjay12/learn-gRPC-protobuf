package com.mynotes.grpc.service.collection;


import com.mynotes.models.collection.Person;
import com.mynotes.models.collection.Person.Address;
import java.util.ArrayList;
import java.util.List;

public class PersonCollectionTest {

    public static void main(String[] args) {

        Address deliveryAddress1 = Address.newBuilder().setHouseNumber(34)
            .setZipCode("1234AA").setCity("Amsterdam").setCountry("Netherlands").build();

        Address deliveryAddress2 = Address.newBuilder().setHouseNumber(11)
            .setZipCode("1234BB").setCity("Amsterdam").setCountry("Netherlands").build();

      Person person = Person.newBuilder().setFName("John").setLName("Doe")
            .setAge(25).addAddresses(deliveryAddress1).addAddresses(deliveryAddress2).build();

      System.out.println(person.toString());


      //Other ways to add address too

      List<Address> addresses = new ArrayList<>();
      addresses.add(deliveryAddress1);
      addresses.add(deliveryAddress2);


      person = Person.newBuilder().setFName("Jane").setLName("Doe")
            .setAge(25).addAllAddresses(addresses).build();

      System.out.println(person.toString());

    }

}
