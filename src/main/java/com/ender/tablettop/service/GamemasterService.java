package com.ender.tablettop.service;

import com.ender.tablettop.domain.Gamemaster;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Gamemaster.
 */
public interface GamemasterService {

    /**
     * Save a gamemaster.
     *
     * @param gamemaster the entity to save
     * @return the persisted entity
     */
    Gamemaster save(Gamemaster gamemaster);

    /**
     * Get all the gamemasters.
     *
     * @return the list of entities
     */
    List<Gamemaster> findAll();


    /**
     * Get the "id" gamemaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Gamemaster> findOne(Long id);

    /**
     * Delete the "id" gamemaster.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
