package com.devdodge.tickets.repositories;

import com.devdodge.tickets.Entites.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
