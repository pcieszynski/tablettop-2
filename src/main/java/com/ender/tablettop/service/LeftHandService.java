package com.ender.tablettop.service;

import com.ender.tablettop.domain.LeftHand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing LeftHand.
 */
public interface LeftHandService {

    /**
     * Save a leftHand.
     *
     * @param leftHand the entity to save
     * @return the persisted entity
     */
    LeftHand save(LeftHand leftHand);

    /**
     * Get all the leftHands.
     *
     * @return the list of entities
     */
    List<LeftHand> findAll();

    /**
     * Get all the LeftHand with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<LeftHand> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" leftHand.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LeftHand> findOne(Long id);

    /**
     * Delete the "id" leftHand.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
