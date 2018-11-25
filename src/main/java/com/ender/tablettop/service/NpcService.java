package com.ender.tablettop.service;

import com.ender.tablettop.domain.Npc;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Npc.
 */
public interface NpcService {

    /**
     * Save a npc.
     *
     * @param npc the entity to save
     * @return the persisted entity
     */
    Npc save(Npc npc);

    /**
     * Get all the npcs.
     *
     * @return the list of entities
     */
    List<Npc> findAll();


    /**
     * Get the "id" npc.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Npc> findOne(Long id);

    /**
     * Delete the "id" npc.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
