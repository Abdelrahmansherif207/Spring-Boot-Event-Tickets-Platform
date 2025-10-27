package com.devdodge.tickets.mappers;

import com.devdodge.tickets.Entites.Event;
import com.devdodge.tickets.dtos.CreateEventRequest;
import com.devdodge.tickets.dtos.events.CreateEventRequestDto;
import com.devdodge.tickets.dtos.events.CreateEventResponseDto;
import com.devdodge.tickets.dtos.CreateTicketTypeRequest;
import com.devdodge.tickets.dtos.ticket_types.CreateTicketTypeRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);
}
