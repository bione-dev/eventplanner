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
            throw new WebApplicationException("Event with ID " + eventId + " not found", 404);
        }
        return event.participants;
    }

    @POST
    @Transactional
    public Participant addParticipant(@PathParam("eventId") Long eventId, Participant participant) {
        Event event = Event.findById(eventId);
        if (event == null) {
            throw new WebApplicationException("Event with ID " + eventId + " not found", 404);
        }

        // Validações básicas
        if (participant.name == null || participant.name.isBlank()) {
            throw new WebApplicationException("Participant name must not be null or empty", 400);
        }
        if (participant.email == null || participant.email.isBlank()) {
            throw new WebApplicationException("Participant email must not be null or empty", 400);
        }

        // Adiciona o participante ao evento
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
            throw new WebApplicationException("Event with ID " + eventId + " not found", 404);
        }

        Participant participant = Participant.findById(participantId);
        if (participant == null) {
            throw new WebApplicationException("Participant with ID " + participantId + " not found", 404);
        }

        if (!event.participants.contains(participant)) {
            throw new WebApplicationException("Participant with ID " + participantId + " is not associated with event ID " + eventId, 400);
        }

        // Remove o participante do evento e persiste as alterações
        event.participants.remove(participant);
        participant.delete(); // Remove o participante do banco de dados
        event.persist();
    }
}
