package com.ender.tablettop.service;

import com.ender.tablettop.domain.Gloves;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Gloves.
 */
public interface GlovesService {

    /**
     * Save a gloves.
     *
     * @param gloves the entity to save
     * @return the persisted entity
     */
    Gloves save(Gloves gloves);

    /**
     * Get all the gloves.
     *
     * @return the list of entities
     */
    List<Gloves> findAll();

    /**
     * Get all the Gloves with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Gloves> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" gloves.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Gloves> findOne(Long id);

    /**
     * Delete the "id" gloves.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
