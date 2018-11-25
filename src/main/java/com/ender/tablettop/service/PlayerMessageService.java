package com.ender.tablettop.service;

import com.ender.tablettop.domain.PlayerMessage;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PlayerMessage.
 */
public interface PlayerMessageService {

    /**
     * Save a playerMessage.
     *
     * @param playerMessage the entity to save
     * @return the persisted entity
     */
    PlayerMessage save(PlayerMessage playerMessage);

    /**
     * Get all the playerMessages.
     *
     * @return the list of entities
     */
    List<PlayerMessage> findAll();


    /**
     * Get the "id" playerMessage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlayerMessage> findOne(Long id);

    /**
     * Delete the "id" playerMessage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
