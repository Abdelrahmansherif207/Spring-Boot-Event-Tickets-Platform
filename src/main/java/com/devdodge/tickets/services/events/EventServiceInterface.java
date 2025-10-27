package com.devdodge.tickets.services.events;

import com.devdodge.tickets.Entites.Event;
import com.devdodge.tickets.dtos.CreateEventRequest;

import java.util.UUID;

public interface EventServiceInterface {
    Event createEvent(UUID organizerId,CreateEventRequest event);

}
