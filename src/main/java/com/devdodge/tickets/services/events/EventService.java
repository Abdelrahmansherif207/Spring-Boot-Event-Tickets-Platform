package com.devdodge.tickets.services.events;

import com.devdodge.tickets.Entites.Event;
import com.devdodge.tickets.Entites.TicketType;
import com.devdodge.tickets.Entites.User;
import com.devdodge.tickets.dtos.CreateEventRequest;
import com.devdodge.tickets.repositories.EventRepository;
import com.devdodge.tickets.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService implements EventServiceInterface {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer = userRepository.findById(organizerId)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format("User with id %s not found", organizerId)
                ));
        List<TicketType> ticketTypes = event
                .getTicketTypes()
                .stream().map(ticketType ->{
                TicketType newTicketType = new TicketType();
                newTicketType.setName(ticketType.getName());
                newTicketType.setPrice(ticketType.getPrice());
                newTicketType.setDescription(ticketType.getDescription());
                newTicketType.setTotalAvailable(ticketType.getTotalAvailable());
                return newTicketType;
        }).toList();

        Event newEvent = new Event();
        newEvent.setTitle(event.getTitle());
        newEvent.setStart(event.getStart());
        newEvent.setEnd(event.getEnd());
        newEvent.setVenue(event.getVenue());
        newEvent.setSalesStart(event.getSalesStart());
        newEvent.setSalesEnd(event.getSalesEnd());
        newEvent.setStatus(event.getStatus());
        newEvent.setOrganizer(organizer);
        newEvent.setTicketTypes(ticketTypes);

        return eventRepository.save(newEvent);
    }
}
