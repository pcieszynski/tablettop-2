package com.ender.tablettop.service;

import com.ender.tablettop.domain.Event;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Event.
 */
public interface EventService {

    /**
     * Save a event.
     *
     * @param event the entity to save
     * @return the persisted entity
     */
    Event save(Event event);

    /**
     * Get all the events.
     *
     * @return the list of entities
     */
    List<Event> findAll();


    /**
     * Get the "id" event.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Event> findOne(Long id);

    /**
     * Delete the "id" event.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<Event> findByGameId(String gameId);
}
