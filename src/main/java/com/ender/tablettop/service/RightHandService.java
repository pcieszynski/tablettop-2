package com.ender.tablettop.service;

import com.ender.tablettop.domain.RightHand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RightHand.
 */
public interface RightHandService {

    /**
     * Save a rightHand.
     *
     * @param rightHand the entity to save
     * @return the persisted entity
     */
    RightHand save(RightHand rightHand);

    /**
     * Get all the rightHands.
     *
     * @return the list of entities
     */
    List<RightHand> findAll();

    /**
     * Get all the RightHand with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<RightHand> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" rightHand.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RightHand> findOne(Long id);

    /**
     * Delete the "id" rightHand.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
