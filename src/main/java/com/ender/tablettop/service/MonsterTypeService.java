package com.ender.tablettop.service;

import com.ender.tablettop.domain.MonsterType;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MonsterType.
 */
public interface MonsterTypeService {

    /**
     * Save a monsterType.
     *
     * @param monsterType the entity to save
     * @return the persisted entity
     */
    MonsterType save(MonsterType monsterType);

    /**
     * Get all the monsterTypes.
     *
     * @return the list of entities
     */
    List<MonsterType> findAll();


    /**
     * Get the "id" monsterType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MonsterType> findOne(Long id);

    /**
     * Delete the "id" monsterType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
