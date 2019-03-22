package com.ender.tablettop.service;

import com.ender.tablettop.domain.Backpack;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Backpack.
 */
public interface BackpackService {

    /**
     * Save a backpack.
     *
     * @param backpack the entity to save
     * @return the persisted entity
     */
    Backpack save(Backpack backpack);

    /**
     * Get all the backpacks.
     *
     * @return the list of entities
     */
    List<Backpack> findAll();

    /**
     * Get all the Backpack with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Backpack> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" backpack.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Backpack> findOne(Long id);

    /**
     * Delete the "id" backpack.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Optional<Backpack> findByCharacterId(Long characterId);
}
