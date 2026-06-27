package com.familytree;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public List<Person> list() {
        return Person.listAll();
    }

    @GET
    @Path("/{id}")
    public Person getById(@PathParam("id") Long id) {
        Person person = Person.findById(id);
        if (person == null) throw new NotFoundException();
        return person;
    }

    @POST
    @Transactional
    public Response create(Person person) {
        Person.persist(person);
        return Response.status(Response.Status.CREATED).entity(person).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Person update(@PathParam("id") Long id, Person updated) {
        Person person = Person.findById(id);
        if (person == null) throw new NotFoundException();
        person.firstName = updated.firstName;
        person.lastName = updated.lastName;
        person.birthYear = updated.birthYear;
        return person;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Person.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
