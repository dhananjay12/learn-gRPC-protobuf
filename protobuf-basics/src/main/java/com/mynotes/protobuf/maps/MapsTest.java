package com.mynotes.protobuf.maps;

import com.mynotes.models.maps.Person;
import com.mynotes.models.maps.Ranks;

public class MapsTest {

    public static void main(String[] args) {
        Person person1 = Person.newBuilder().setFName("John").setLName("Doe").setAge(25).build();
        Person person2 = Person.newBuilder().setFName("Jane").setLName("Doe").setAge(27).build();

        Ranks ranks = Ranks.newBuilder()
            .putRanks(1, person1)
            .putRanks(2, person2).build();

        // ranks is a wrapper around java map
        System.out.println("Total=" + ranks.getRanksCount());
        System.out.println("Rank 1="+ranks.getRanksOrThrow(1));
        System.out.println("Default="+ranks.getRanksOrDefault(3, person2));
        // For java map use getRanksMap
        System.out.println("Java Map="+ranks.getRanksMap().get(1));
        // Exceptions
        System.out.println(ranks.getRanksOrThrow(3));
    }

}
