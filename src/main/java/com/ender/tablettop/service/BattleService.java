package com.ender.tablettop.service;

import com.ender.tablettop.domain.Battle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Battle.
 */
public interface BattleService {

    /**
     * Save a battle.
     *
     * @param battle the entity to save
     * @return the persisted entity
     */
    Battle save(Battle battle);

    /**
     * Get all the battles.
     *
     * @return the list of entities
     */
    List<Battle> findAll();

    /**
     * Get all the Battle with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Battle> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" battle.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Battle> findOne(Long id);

    /**
     * Delete the "id" battle.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
