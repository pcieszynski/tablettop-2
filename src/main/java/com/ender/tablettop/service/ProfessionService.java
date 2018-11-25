package com.ender.tablettop.service;

import com.ender.tablettop.domain.Profession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Profession.
 */
public interface ProfessionService {

    /**
     * Save a profession.
     *
     * @param profession the entity to save
     * @return the persisted entity
     */
    Profession save(Profession profession);

    /**
     * Get all the professions.
     *
     * @return the list of entities
     */
    List<Profession> findAll();

    /**
     * Get all the Profession with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Profession> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" profession.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Profession> findOne(Long id);

    /**
     * Delete the "id" profession.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
