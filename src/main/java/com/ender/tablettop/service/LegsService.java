package com.ender.tablettop.service;

import com.ender.tablettop.domain.Legs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Legs.
 */
public interface LegsService {

    /**
     * Save a legs.
     *
     * @param legs the entity to save
     * @return the persisted entity
     */
    Legs save(Legs legs);

    /**
     * Get all the legs.
     *
     * @return the list of entities
     */
    List<Legs> findAll();

    /**
     * Get all the Legs with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Legs> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" legs.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Legs> findOne(Long id);

    /**
     * Delete the "id" legs.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
