package com.ender.tablettop.service;

import com.ender.tablettop.domain.Helmet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Helmet.
 */
public interface HelmetService {

    /**
     * Save a helmet.
     *
     * @param helmet the entity to save
     * @return the persisted entity
     */
    Helmet save(Helmet helmet);

    /**
     * Get all the helmets.
     *
     * @return the list of entities
     */
    List<Helmet> findAll();

    /**
     * Get all the Helmet with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Helmet> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" helmet.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Helmet> findOne(Long id);

    /**
     * Delete the "id" helmet.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
