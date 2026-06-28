package com.familytree;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        // Load a Person record from the database.
        List<Person> people = Person.listAll();
        Person person = people.isEmpty() ? null : people.getFirst();

        if (person != null) {
            return "Hello from FamilyTree! First person: " + person.firstName + " " + person.lastName;
        }
        return "Hello from FamilyTree!";
    }
}
