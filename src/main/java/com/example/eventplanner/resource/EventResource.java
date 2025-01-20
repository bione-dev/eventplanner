package com.example.eventplanner.resource;

import com.example.eventplanner.model.Event;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    @GET
    public List<Event> getAllEvents() {
        return Event.listAll();
    }

    @POST
    @Transactional
    public Event createEvent(@Valid Event event) {
        event.persist();
        return event;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Event updateEvent(@PathParam("id") Long id, @Valid Event updatedEvent) {
        Event event = Event.findById(id);
        if (event == null) {
            throw new WebApplicationException("Event not found", 404);
        }
        event.name = updatedEvent.name;
        event.date = updatedEvent.date;
        event.location = updatedEvent.location;
        event.persist();
        return event;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteEvent(@PathParam("id") Long id) {
        Event event = Event.findById(id);
        if (event == null) {
            throw new WebApplicationException("Event not found", 404);
        }
        event.delete();
    }
}
