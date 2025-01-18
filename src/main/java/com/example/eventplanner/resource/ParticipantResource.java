package com.example.eventplanner.resource;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.Participant;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/events/{eventId}/participants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParticipantResource {

    @GET
    public List<Participant> getParticipants(@PathParam("eventId") Long eventId) {
        Event event = Event.findById(eventId);
        if (event == null) {
            throw new WebApplicationException("Event not found", 404);
        }
        return event.participants;
    }

    @POST
    @Transactional
    public Participant addParticipant(@PathParam("eventId") Long eventId, Participant participant) {
        Event event = Event.findById(eventId);
        if (event == null) {
            throw new WebApplicationException("Event not found", 404);
        }
        event.participants.add(participant);
        event.persist();
        return participant;
    }

    @DELETE
    @Path("/{participantId}")
    @Transactional
    public void removeParticipant(@PathParam("eventId") Long eventId, @PathParam("participantId") Long participantId) {
        Event event = Event.findById(eventId);
        if (event == null) {
            throw new WebApplicationException("Event not found", 404);
        }
        Participant participant = Participant.findById(participantId);
        if (participant == null || !event.participants.contains(participant)) {
            throw new WebApplicationException("Participant not found in event", 404);
        }
        event.participants.remove(participant);
        participant.delete();
        event.persist();
    }
}
